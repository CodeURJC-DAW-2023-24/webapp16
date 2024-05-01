import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import {API_URL} from "../../config";
import {SessionService} from "./session.service";
import {Router} from "@angular/router";
import {Match} from "../models/match.model";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private apiUrl = `${API_URL}/matches`;

  constructor(private http: HttpClient, private router: Router) { }

  getMatches(id: string): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.apiUrl}/tournament/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        //console.error('Error occurred while fetching matches:', err);
        this.router.navigate(['/error']);
        return new Observable<Match[]>();
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










}


