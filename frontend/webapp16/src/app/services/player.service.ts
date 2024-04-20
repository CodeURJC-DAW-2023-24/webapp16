import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Player } from '../models/player.model';
import { SessionService } from './session.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import {API_URL} from "../../config";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiUrl = `${API_URL}/players`;
  private statisticsUrl = `${API_URL}/statistics/players`;

  constructor(private http: HttpClient, private sessionService: SessionService, private router: Router) { }

  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.apiUrl);
  }

  // player.service.ts

getTopPlayers(): Observable<Player[]> {
  return this.http.get<Player[]>(this.statisticsUrl).pipe(
    catchError(err => {
      console.error('Error occurred while fetching players:', err);
      //this.router.navigate(['/error']);
      return throwError(err);
    })
  );
}
}
