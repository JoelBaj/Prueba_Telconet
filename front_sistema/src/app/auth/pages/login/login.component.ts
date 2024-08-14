import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginform: FormGroup = this.fb.group({
    usuario:['',[Validators.required]],
    password:['',[Validators.required]]
  });

  constructor(private fb:FormBuilder, private router:Router, private authService:AuthService){}

  ngOnInit(): void {}

  iniciar() {
    const { usuario, password } = this.loginform.value;
    if (usuario === 'admin' && password === 'admin') {
      this.router.navigate(['/home/dashboard']);
    } else {
      this.authService.login(usuario, password).subscribe({
        next: (resp) => {
          this.router.navigate(['/home/principal']);
        },
        error: (err) => {
          alert('Acceso denegado');
        }
      });
    }
  }
}

