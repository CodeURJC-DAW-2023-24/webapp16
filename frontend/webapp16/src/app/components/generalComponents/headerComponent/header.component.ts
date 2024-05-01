import { Component } from '@angular/core';
import {query} from "@angular/animations";
import {Router} from "@angular/router";
import {SearchService} from "../../../services/search.service";
import { AuthService } from '../../../services/auth.service';
import {throwError} from "rxjs";

@Component({
  selector: 'header-web',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})

export class HeaderComponent {
  isAuth: boolean = false;

  constructor(private router: Router, private searchService: SearchService, private authService: AuthService) { }

  ngOnInit() {
    this.authService.isLoggedIn.subscribe(
      (loggedIn: boolean) => {
        this.isAuth = loggedIn;
      }
    )
  }


  query =""; //search query
  results: any[] = [];

  search(query: string): void {
    this.router.navigate(['/search'], { queryParams: { query: query } });
  }

 searchAll(query: string): void {
    console.log("Making request to get players...");
   let parts = query.split(":"); //  array ["type", "name"]
   let type = parts[0].trim();
   console.log("Type: ", type);
    this.searchService.searchAll(query).subscribe({
      next: (results) => {
        console.log('Received players data:', results);
        this.results = results;
        this.searchService.setData(results);
        this.searchService.setType(type);
        this.router.navigate(['/search']);
      },
      error: (error) => {
        const errorCode = error.status;
        this.router.navigate(['/error'], { state: { errorCode: errorCode } });
        return throwError(error);
        //console.error('Error occurred while fetching players:', error);
      }
    });

  }
    //protected query = query;
}
