package com.back_sistema.api.SecurityConfig;


import com.back_sistema.api.model.entity.Residente;
import com.back_sistema.api.model.repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service

public class CustomUserDetailsService implements UserDetailsService{

    private ResidenteRepository residenteRepository;

    @Autowired
    public CustomUserDetailsService(ResidenteRepository residenteRepository) {
        this.residenteRepository = residenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Residente residente = residenteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("residente no encontrado"));
        return new User(residente.getUsername(), residente.getPassword(), List.of());
    }



}
