import { HttpClient } from '@angular/common/http';
import {Observable, from, toArray, throwError, concatMap} from 'rxjs';
import { map, switchMap, catchError } from 'rxjs/operators';
import {API_URL} from "../../config";
import {Tournament} from "../models/tournament.model";
import {Injectable} from "@angular/core";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class TournamentService {
  private apiUrl = `${API_URL}/tournaments`;

  constructor(private http: HttpClient, private router:Router) {
  }

  getBase64ImageFromURL(url: string): Promise<string> {
    return this.http.get(url, { responseType: 'blob'}).toPromise()
      .then(blob => {
        let reader = new FileReader();
        reader.readAsDataURL(<Blob>blob);
        return new Promise<string>((resolve, reject) => {
          reader.onloadend = () => resolve(reader.result as string);
          reader.onerror = reject;
        });
      });
  }

getTournament(): Observable<Tournament[]> {
  return this.http.get<Tournament[]>(this.apiUrl, {withCredentials: true}).pipe(
    catchError(error => {
      console.error('Error occurred while fetching tournaments:', error);
      return throwError(error);
    })
  );
}

  getTournamentById(id: number) : Observable<Tournament> {
    return this.http.get<Tournament>(`${this.apiUrl}/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        if (err.status === 404) {
          // Manejar específicamente el error 404
          console.error('Tournament not found:', err);
          this.router.navigate(['/error'], {state: {errorCode: 404}});
        } else {
          // Manejar otros errores
          console.error('Error occurred while fetching tournament:', err);
        }
        return throwError(err);
      })
    );

  }
}
