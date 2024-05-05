import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private isSearchActive: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  private dataSubject: BehaviorSubject<any[]> = new BehaviorSubject<any[]>([]);
  private typeSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');

  constructor(private http: HttpClient) { }

  search(query: string): Observable<any> {
    this.isSearchActive.next(true);
    return this.http.get(`/api/search?query=${query}`,{withCredentials:true});
  }

  searchAll(query: string): Observable<any> {
    this.isSearchActive.next(true);
    query = query.replace(":", "="); //replace : with =
    return this.http.get(`/api/search?${query}`,{withCredentials:true});
  }

  setData(data: any) {
    this.dataSubject.next(data);
  }

  getData(): Observable<any[]> {
    return this.dataSubject.asObservable();
  }

  setType(type: string) {
    this.typeSubject.next(type);
  }

  getType(): Observable<string> {
    return this.typeSubject.asObservable();
  }
  getIsSearchActive(): Observable<boolean> {
    return this.isSearchActive.asObservable();
  }

  setIsSearchActive(isActive: boolean): void {
    this.isSearchActive.next(isActive);
  }
}
