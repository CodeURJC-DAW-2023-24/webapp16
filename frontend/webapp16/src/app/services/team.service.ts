import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Team } from '../models/team.model';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import {API_URL} from "../../config";
import {Player} from "../models/player.model";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private apiUrl = `${API_URL}/teams`;
  private apiPlayersUrl = `${API_URL}/players`;
  private apiUrlPage = `${API_URL}/teams?page=`;
  private statisticsUrl = `${API_URL}/statistics/teams`;

  constructor(private http: HttpClient, private router: Router) { }

 getTeams(page: number): Observable<Team[]> {
  return this.http.get<Team[]>(`${this.apiUrlPage}${page}`, {withCredentials: true}).pipe(
    catchError(err => {
      //console.error('Error occurred while fetching teams:', err);
      return throwError(err);
    })
  );
}

  // team.service.ts
  getTopTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.statisticsUrl,{withCredentials:true}).pipe(
      catchError(err => {
        //console.error('Error occurred while fetching teams:', err);
        return throwError(err);
      })
    );
  }
getTeam(id:string): Observable<Team> {
  return this.http.get<Team>(`${this.apiUrl}/${id}`, {withCredentials: true}).pipe(
    catchError(err => {
      if (err.status === 404) {
        // Manejar específicamente el error 404
        console.error('Team not found:', err);
        this.router.navigate(['/error'], {state: {errorCode: 404}});
      } else {
        // Manejar otros errores
        console.error('Error occurred while fetching team:', err);
      }
      return throwError(err);
    })
  );
}

  getTeamPlayers(id: string): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.apiPlayersUrl}/team/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        //console.error('Error occurred while fetching team players:', err);
        return throwError(err);
      })
    );
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

  updateTeam(winningTeam: Team) {
    return this.http.put(`${this.apiUrl}/${winningTeam.id}`, winningTeam, {observe: 'response', withCredentials: true}).pipe(
catchError(err => {
        //console.error('Error occurred while updating team:', err);
        return throwError(err);
      })
    );

  }
}
