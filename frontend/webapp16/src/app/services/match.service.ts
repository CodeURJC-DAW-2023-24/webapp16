import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, BehaviorSubject, throwError} from 'rxjs';
import {API_URL} from "../../config";
import {Router} from "@angular/router";
import {Match} from "../models/match.model";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private apiUrl = `${API_URL}/matches`;

  constructor(private http: HttpClient, private router: Router) { }

  getMatches(id: string): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.apiUrl}/tournament/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        if (err.status === 404) {
          // Manejar específicamente el error 404
          console.error('Tournaments matches not found:', err);
          this.router.navigate(['/error'], {state: {errorCode: 404}});
        } else {
          // Manejar otros errores
          console.error('Error occurred while fetching tournaments matches:', err);
        }
        return throwError(err);
      })
    );

  }
  getMatchesById(id: string): Observable<Match> {
    return this.http.get<Match>(`${this.apiUrl}/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        //console.error('Error occurred while fetching matches:', err);
        this.router.navigate(['/error']);
        return new Observable<Match>();
      })
    );

  }




  postMatch(match: Match) {
    return this.http.post(`${this.apiUrl}`, match, {observe: 'response', withCredentials: true}).pipe(tap(response => {
      if (response.status === 201) {
        console.log("Match created") // Update loggedIn status
      }
      this.router.navigate(['/']);
    }));

  }

  putMatch(match: Match) {
    return this.http.put(`${this.apiUrl}/${match.id}`, match, {observe: 'response', withCredentials: true}).pipe(tap(response => {
      if (response.status === 200) {
        console.log("Match updated") // Update loggedIn status
      }
      this.router.navigate(['/']);
    }));

  }
}


