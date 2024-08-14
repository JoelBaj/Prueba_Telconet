package com.back_sistema.api.controller;

import com.back_sistema.api.SecurityConfig.CustomUserDetailsService;
import com.back_sistema.api.SecurityConfig.JWTGenerator;
import com.back_sistema.api.model.Dto.AuthResponseDTO;
import com.back_sistema.api.model.Dto.CreateResidenteDto;
import com.back_sistema.api.model.Dto.LoginDto;
import com.back_sistema.api.model.entity.Residente;
import com.back_sistema.api.model.repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private AuthenticationManager authenticationManager;
    private ResidenteRepository residenteRepository;

    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, ResidenteRepository residenteRepository,
                          PasswordEncoder passwordEncoder,
                          JWTGenerator jwtGenerator, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.residenteRepository = residenteRepository;

        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.customUserDetailsService = customUserDetailsService;
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getUsername());
        Residente residente = residenteRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        String token = jwtGenerator.generateToken(authentication);
        AuthResponseDTO responseDTO = new AuthResponseDTO(token);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody CreateResidenteDto createResidenteDto) {
        Residente residente = new Residente();
        residente.setUsername(createResidenteDto.getUsername());
        residente.setPlaca(createResidenteDto.getPlaca());
        residente.setNombre(createResidenteDto.getNombre());
        residente.setApellido(createResidenteDto.getApellido());
        residente.setIdentificacion(createResidenteDto.getIdentificacion());
        residente.setPassword(passwordEncoder.encode(createResidenteDto.getPassword()));

        residenteRepository.save(residente);


        return new ResponseEntity<>(Map.of("message", "residente registrado correctamente"), HttpStatus.OK);
    }

}
