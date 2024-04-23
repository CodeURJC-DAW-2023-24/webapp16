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
    return this.http.get<Team[]>(this.apiUrl,{withCredentials:true});
  }

  // team.service.ts
  getTopTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.statisticsUrl,{withCredentials:true}).pipe(
      catchError(err => {
        console.error('Error occurred while fetching teams:', err);
        return throwError(err);
      })
    );
  }
  getTeam(id: number): Observable<Team> {
    return this.http.get<Team>(`${this.apiUrl}/teams/${id}`,{withCredentials:true});
  }
  getBase64ImageFromURL(url: string): Promise<string> {
    return this.http.get(url, { responseType: 'blob' ,withCredentials:true}).toPromise()
      .then(blob => {
        let reader = new FileReader();
        reader.readAsDataURL(<Blob>blob);
        return new Promise<string>((resolve, reject) => {
          reader.onloadend = () => resolve(reader.result as string);
          reader.onerror = reject;
        });
      });
  }
}
