// auth.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import {Router} from "@angular/router";
import { CookieService } from 'ngx-cookie-service';
import {API_URL} from "../../config";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router:Router, private cookieService: CookieService) { }

  login(username: string, password: string): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${API_URL}/auth/login`, { username, password }, { observe: 'response', withCredentials: true })
      .pipe(tap(response => {
        // ...
        const accessToken = this.cookieService.get('AuthToken'); // Reemplaza 'access_token_name' con el nombre real de tu cookie de token de acceso
        const refreshToken = this.cookieService.get('RefreshToken'); // Reemplaza 'refresh_token_name' con el nombre real de tu cookie de token de actualización
        console.log(refreshToken,accessToken)
        if (accessToken)
          localStorage.setItem('accessToken', accessToken); // Almacena el token de acceso
        if (refreshToken)
          localStorage.setItem('refreshToken', refreshToken); // Almacena el token de actualización
        this.router.navigate(['/']);
      }));
  }
    /*login(username: string, password: string): Observable<any> {
      return this.http.post<any>(`${this.API_URL}/auth/login`, { username, password })
        .pipe(tap(user => {
          localStorage.setItem('user', JSON.stringify(user));
          this.router.navigate(['/']);
        }));
    } */


  logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }

  isLoggedIn() {
    return !!localStorage.getItem('user');
  }
}
