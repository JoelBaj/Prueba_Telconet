import { Component } from '@angular/core';
import {  Router } from '@angular/router';
import { LocalService } from 'src/app/auth/services/local.service';
interface MenuItem{
  texto:string;
  ruta:string;
}
@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.css']
})
export class SidemenuComponent {

  constructor(private router:Router,
              private local:LocalService) { }

  Menu:MenuItem[] =[
    {texto:'Principal', ruta:'/home/principal'},

  ]

  logout() { 
    this.local.removeItem('token');
    this.router.navigate(['/login'])
  }

}
