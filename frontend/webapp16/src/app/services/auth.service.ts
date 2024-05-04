// auth.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';
import {Observable, of, throwError} from 'rxjs';
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


  // auth.service.ts
  login(username: string, password: string): Observable<boolean> {
    return this.http.post<any>(`${API_URL}/auth/login`, { username, password }, { observe: 'response', withCredentials: true })
      .pipe(
        map(response => {
          if (response.status === 200) {
            this.loggedIn.next(true); // Update loggedIn status
            this.router.navigate(['/']);
            return true;
          }
          return false; // Add this line
        }),
        catchError(error => {
          if (error.status === 401) {
            // Handle incorrect password
            return of(false);
          } else {
            return throwError(error);
          }
        })
      );
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

  register(name: string, email: string, password: string , date: string): Observable<any> {
    return this.http.post(`${API_URL}/users/`, {name, email, password, date, roles: ["USER"]}, { observe: 'response', withCredentials: true })
      .pipe(tap(response => {
      if (response.status === 200) {
        this.loggedIn.next(true); // Update loggedIn status
      }
      this.router.navigate(['/']);
    }));
  }
}
