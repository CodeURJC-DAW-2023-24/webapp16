import { HttpClient } from '@angular/common/http';
import {Observable, from, toArray, throwError, concatMap} from 'rxjs';
import { map, switchMap, catchError } from 'rxjs/operators';
import {API_URL} from "../../config";
import {Tournament} from "../models/tournament.model";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class TournamentService {
  private apiUrl = `${API_URL}/tournaments`;

  constructor(private http: HttpClient) {
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
      catchError(error => {
        console.error('Error occurred while fetching tournament:', error);
        return throwError(error);
      })
    );

  }
  createTournament(tournament: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/$`, tournament, {withCredentials: true}).pipe(
      catchError(error => {
        console.error('Error occurred while creating tournament:', error);
        return throwError(error);
      })
    );
  }
}
