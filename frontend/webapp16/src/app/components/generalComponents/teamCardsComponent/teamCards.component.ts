import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Team} from "../../../models/team.model";
import {TeamService} from "../../../services/team.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import { PaginationService } from '../../../services/pagination.service';
import { Router } from '@angular/router';
import {SearchService} from "../../../services/search.service";

@Component({
  selector: 'teamCard',
  templateUrl: './teamCards.component.html',
  styleUrls: ['./teamCards.component.css']
})
export class TeamCardsComponent implements OnInit{
  @Input() teamData: any;
  @Input() loadDefaultTeams: boolean = true;
  @Output() hasMoreDataChange = new EventEmitter<boolean>();
  page: number = 0;
  hasMoreData: boolean = true;
  isSearchActive: boolean = false;

  constructor(private teamsService: TeamService, private paginationService: PaginationService, private router: Router, private searchService: SearchService) {
    this.searchService.getIsSearchActive().subscribe(isActive => {
      this.isSearchActive = isActive;
    });

    this.paginationService.currentPage.subscribe(page => {
      this.page = page;
      console.log('currentPage:', this.page);
      if (!this.isSearchActive) {
        console.log('Constructor Search active', this.isSearchActive);
        this.loadMoreTeams();
      }
    });
  }

  ngOnInit(): void {
    this.page= 0;
    console.log('ngOnInit page:', this.page);
    this.isSearchActive = this.router.url.startsWith('/search');
  }
  ngOnDestroy(): void {
    this.paginationService.resetPage();
  }
  loadMoreTeams(): void {
    if (!this.isSearchActive) {
      console.log('LoadMore Search active', this.isSearchActive);
      console.log('loadMoreTeams called');
      this.teamsService.getTeams(this.page).pipe(
        catchError(error => {
          const errorCode = error.status;
          this.router.navigate(['/error'], {state: {errorCode: errorCode}});
          //console.error('Error occurred while fetching teams:', error);
          return throwError(error);
        })
      ).subscribe({
        next: (teams) => {
          if (teams.length === 0) {
            this.hasMoreData = false;
            this.hasMoreDataChange.emit(this.hasMoreData);
            console.log('No more teams to load', this.hasMoreData);
            return;
          }
          // If teamData is not initialized, initialize it with the teams from the first page
          if (!this.teamData) {
            this.teamData = teams;
          } else {
            // If teamData is already initialized, add the new teams to the end of the existing teams
            this.teamData = [...this.teamData, ...teams];
          }
          console.log('hasMoreData:', this.hasMoreData);
          this.page += 1;
        },
        error: (error) => {
          const errorCode = error.status;
          this.router.navigate(['/error'], {state: {errorCode: errorCode}});
          return throwError(error);
          console.error('Error occurred while fetching teams:', error);
        }
      });
    }
  }
}
