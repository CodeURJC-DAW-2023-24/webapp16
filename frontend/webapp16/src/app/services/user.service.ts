import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../models/user.model'; // Import UserProfile model
import {API_URL} from "../../config";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = `${API_URL}/users`;

  constructor(private http: HttpClient) { }

  // Method to get user profile
  getUserProfile(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/me`).pipe(
      catchError(err => {
        console.error('Error occurred while fetching user profile:', err);
        return throwError(err);
      })
    );
  }

  // Method to update user profile
  updateUserProfile(userProfile: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/me`, userProfile).pipe(
      catchError(err => {
        console.error('Error occurred while updating user profile:', err);
        return throwError(err);
      })
    );
  }
}
