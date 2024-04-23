import {Component, Input, OnInit} from '@angular/core';
import {Team} from "../../../models/team.model";
import {TeamService} from "../../../services/team.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";

@Component({
  selector: 'teamCard',
  templateUrl: './teamCards.component.html',
  styleUrl: './teamCards.component.css'
})
export class TeamCardsComponent implements OnInit{
  @Input() teamData: any;
  constructor(private teamsService: TeamService) {

  }
  ngOnInit(): void {
    if (!this.teamData || this.teamData.length === 0) {
      console.log('Making request to get teams...');
      this.teamsService.getTeams().pipe(
        catchError(error => {
          console.error('Error occurred while fetching teams:', error);
          return throwError(error);
        })
      ).subscribe({
        next: (team) => {
          console.log('Received teams data:', team);
          this.teamData = team
        },
        error: (error) => {
          console.error('Error occurred while fetching teams:', error);
        }
      });
    }
  }





}
