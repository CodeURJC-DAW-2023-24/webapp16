// auth.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import {Router} from "@angular/router";
import { CookieService } from 'ngx-cookie-service';
import {API_URL} from "../../config";
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }
  constructor(private http: HttpClient, private router:Router, private cookieService: CookieService) { }

  login(username: string, password: string): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${API_URL}/auth/login`, { username, password }, { observe: 'response', withCredentials: true })
      .pipe(tap(response => {
        if (response.status === 200) {
          this.loggedIn.next(true); // Update loggedIn status
        }
        this.router.navigate(['/']);
      }));
  }

  logout():Observable<HttpResponse<any>> {
    return this.http.post<any>(`${API_URL}/auth/logout`, {}, { observe: 'response', withCredentials: true })
      .pipe(tap(response => {
        if (response.status === 200) {
          this.loggedIn.next(false); // Update loggedIn status
        }
        this.router.navigate(['/']);
      }));
  }

  register(name: string, password: string, email: string, date: Date): Observable<any> {
    return this.http.post(`${API_URL}/users`, {name, password, email, date}, { observe: 'response', withCredentials: true })
      .pipe(tap(response => {
      if (response.status === 200) {
        this.loggedIn.next(true); // Update loggedIn status
      }
      this.router.navigate(['/']);
    }));
  }
}
