import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Team } from '../models/team.model';
import { SessionService } from './session.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import {API_URL} from "../../config";
import {Player} from "../models/player.model";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private apiUrl = `${API_URL}/teams`;
  private statisticsUrl = `${API_URL}/statistics/teams`;

  constructor(private http: HttpClient, private sessionService: SessionService, private router: Router) { }

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.apiUrl);
  }

  // team.service.ts
  getTopTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.statisticsUrl).pipe(
      catchError(err => {
        console.error('Error occurred while fetching teams:', err);
        return throwError(err);
      })
    );
  }
}
