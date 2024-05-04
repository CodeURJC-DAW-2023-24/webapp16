import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Team} from "../../../models/team.model";
import {TeamService} from "../../../services/team.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import { PaginationService } from '../../../services/pagination.service';
import { Router } from '@angular/router';

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

  constructor(private teamsService: TeamService, private paginationService: PaginationService, private router: Router) {
    this.paginationService.currentPage.subscribe(page => {
      this.page = page;
      //this.loadMoreTeams();
    });
  }

  ngOnInit(): void {
    this.page= 0;
    if (this.loadDefaultTeams) {
      this.loadMoreTeams();
    }
  }
  ngOnDestroy(): void {
    this.paginationService.resetPage();
  }
  loadMoreTeams(): void {
  this.teamsService.getTeams(this.page).pipe(
    catchError(error => {
      const errorCode = error.status;
      this.router.navigate(['/error'], { state: { errorCode: errorCode } });
     //console.error('Error occurred while fetching teams:', error);
      return throwError(error);
    })
    ).subscribe({
  next: (teams) => {
    if (teams.length === 0) {
      this.hasMoreData = false;
      return;
    }
    // If teamData is not initialized, initialize it with the teams from the first page
    if (!this.teamData) {
      this.teamData = teams;
    } else {
      // If teamData is already initialized, add the new teams to the end of the existing teams
      this.teamData = [...this.teamData, ...teams];
    }
    this.page += 1;
  },
  error: (error) => {
    const errorCode = error.status;
    this.router.navigate(['/error'], { state: { errorCode: errorCode } });
    return throwError(error);
    console.error('Error occurred while fetching teams:', error);
  }
});
  }
}
