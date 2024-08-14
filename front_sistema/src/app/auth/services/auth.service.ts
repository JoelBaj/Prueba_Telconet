import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { LocalService } from './local.service';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { residente } from '../models/residentes';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private Urlapi: string = environment.loginUrl;
  private registerUrl: string = environment.RegistroUrl;

  constructor(private http:HttpClient, 
              private local:LocalService) { }


  login(username: string, password: string): Observable<any> {
    const body = { username, password };
    return this.http.post<any>(this.Urlapi, body).pipe(
      tap(response => {
        this.local.setItem('token', response.accessToken);
      })
    );
  }

  register(res: residente): Observable<residente> {
    return this.http.post<residente>(this.registerUrl, res);
  }
              
}
