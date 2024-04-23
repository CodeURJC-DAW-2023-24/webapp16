import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }
 data: any [] = [];
  type = ''; //type of search
  search(query: string): Observable<any> {
    return this.http.get(`/api/search?query=${query}`);
  }
  searchPlayer(query: string): Observable<any> {
    console.log("query received in searchPlayer: ", query)
    query = query.replace(":", "="); //replace : with =
    console.log("query after replace:", query)
    return this.http.get(`/api/search?${query}`);
  }


  setData(data: any) {
    this.data = data;
  }
  getData() {
    return this.data;
  }
  setType(type: string) {
    this.type = type;
  }
  getType() {
    return this.type;
  }
}
