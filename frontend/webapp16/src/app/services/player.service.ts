import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Player } from '../models/player.model';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import {API_URL} from "../../config";
import {Team} from "../models/team.model";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private apiUrl = `${API_URL}/players`;
  private apiUrlPage = `${API_URL}/players?page=`;
  private statisticsUrl = `${API_URL}/statistics/players`;

  constructor(private http: HttpClient, private router: Router) { }


  getPlayers(page: number): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.apiUrlPage}${page}`, {withCredentials: true}).pipe(
      catchError(err => {
        //console.error('Error occurred while fetching players:', err);
        return throwError(err);
      })
    );
  }

  // player.service.ts

getTopPlayers(): Observable<Player[]> {
  return this.http.get<Player[]>(this.statisticsUrl, {withCredentials:true}).pipe(
    catchError(err => {
      //console.error('Error occurred while fetching players:', err);
      this.router.navigate(['/error']);
      return throwError(err);
    })
  );
}
getPlayer(id: number): Observable<Player> {
  return this.http.get<Player>(`${this.apiUrl}/${id}`, {withCredentials: true}).pipe(
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
  getBase64ImageFromURL(url: string): Promise<string> {
    return this.http.get(url, { responseType: 'blob' }).toPromise()
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
