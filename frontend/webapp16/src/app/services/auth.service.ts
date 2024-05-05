// auth.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {catchError, map, switchMap, tap} from 'rxjs/operators';
import {Observable, of, throwError} from 'rxjs';
import {Router} from "@angular/router";
import { CookieService } from 'ngx-cookie-service';
import {API_URL} from "../../config";
import { BehaviorSubject } from 'rxjs';
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);
  private user = new BehaviorSubject<User | null>(null);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }
  get currentUser() { // Add this getter
    return this.user.asObservable();
  }

  constructor(private http: HttpClient, private router:Router) { }


  // auth.service.ts
  // auth.service.ts
  login(username: string, password: string): Observable<boolean> {
    console.log('Login method called with username: ', username);
    return this.http.post<any>(`${API_URL}/auth/login`, { username, password }, { observe: 'response', withCredentials: true })
      .pipe(
        switchMap(response => {
          console.log('Response from login: ', response);
          if (response.status === 200) {
            this.loggedIn.next(true);
            console.log('Login successful. Updating loggedIn status...');
            this.router.navigate(['/']);
            return this.getUser().pipe( // Fetch the user after successful login
              tap(user => {
                console.log('User from login: ', user);
                if (user) {
                  console.log('User roles: ', user.roles);
                  this.isAdmin().subscribe(isAdmin => {
                    console.log("Is admin: " + isAdmin);
                  });
                }
              }),
              map(() => true) // Always return true after successful login
            );
          }
          console.log('Login failed');
          return of(false);
        }),
        catchError(error => {
          console.log('Error in login: ', error);
          if (error.status === 401) {
            // Handle incorrect password
            return of(false);
          } else if (error.status === 404) {
            // Handle user not found
            return throwError('User not found');
          } else {
            return throwError(error);
          }
        })
      );
  }

  logout(): Observable<HttpResponse<any>> {
    console.log('Logging out...');
    return this.http.post<any>(`${API_URL}/auth/logout`, {}, { observe: 'response', withCredentials: true })
      .pipe(tap(response => {
        if (response.status === 200) {
          console.log('Logout successful. Updating loggedIn status...');
          this.loggedIn.next(false); // Update loggedIn status
          this.user.next(null); // Clear the current user
          // Reload the current page
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.router.navigate([this.router.url]);
          });
        }
      }));
  }

  register(name: string, email: string, password: string , date: string): Observable<any> {
    console.log('Date of birth in register service:', date);
    return this.http.post(`${API_URL}/users/`, {name, email, password, date, roles: ["USER"]}, { observe: 'response', withCredentials: true })
      .pipe(tap(response => {
      if (response.status === 200) {
        this.loggedIn.next(true); // Update loggedIn status
      }
      this.router.navigate(['/']);
    }));
  }
  isUsernameChanged(currentUsername: string, updatedUsername: string): boolean {
    return currentUsername !== updatedUsername;
  }
  usernameExists(username: string): Observable<boolean> {
    return this.http.get<boolean>(`/api/me/usernameExists/${username}`);
  }
  getUser(): Observable<User> {
    return this.http.get<User>(`${API_URL}/me`, { withCredentials: true })
      .pipe(
        tap(user => {
          console.log('User from getUser: ', user);
          this.user.next(user);
        }),
        catchError(error => {
          console.log('Error in getUser: ', error); // Add this line
          throw error;
        })
      );
  }

  // Add isAdmin method to check if user is an admin
  isAdmin(): Observable<boolean> {
    return this.currentUser.pipe(
      map(user => {
        if (user?.roles) {
          return user.roles.includes('ADMIN');
        }
        return false;
      })
    );
  }
}
