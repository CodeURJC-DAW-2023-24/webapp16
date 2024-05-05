import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import {API_URL} from "../../config";
import {SessionService} from "./session.service";
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

  getReportById(id: string): Observable<Report> {
    return this.http.get<Report>(`${API_URL}/reports/matchReport/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        //console.error('Error occurred while fetching report:', err);
        this.router.navigate(['/error']);
        return new Observable<Report>();
      })
    );
  }

  postMatchReport(date: any, time: any, matchOfficials: any, localTeamGoals: any, visitingTeamGoals: any, observations: any, match: any): Observable<any> {
  return this.http.post(`${API_URL}/reports/`,
    {date,time, matchOfficials,localTeamGoals,visitingTeamGoals, observations, match},
    {observe: 'response', withCredentials: true}).pipe(tap(response => {
    if (response.status === 200) {
      console.log("update rounds") // Update loggedIn status
    }
    this.router.navigate(['/']);
  }));
}

  postMatchReport2(report:any): Observable<any> {
    return this.http.post(`${API_URL}/reports/`,
      report,
      {observe: 'response', withCredentials: true}).pipe(tap(response => {
      if (response.status === 200) {
        console.log("update rounds") // Update loggedIn status
      }
      this.router.navigate(['/']);
    }));
  }







}


