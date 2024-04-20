import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Player } from '../models/player.model';
import { SessionService } from './session.service';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiUrl = '/api/players';
  private statisticsUrl = '/api/statistics/players';

  constructor(private http: HttpClient, private sessionService: SessionService, private router: Router) { }

  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.apiUrl);
  }

  // player.service.ts

getTopPlayers(): Observable<Player[]> {
  return this.http.get<Player[]>(this.statisticsUrl).pipe(
    catchError(err => {
      this.router.navigate(['/error']);
      return throwError(err);
    })
  );
}
}
