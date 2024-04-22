import { Component } from '@angular/core';
import {Team} from "../../../models/team.model";
import {TeamService} from "../../../services/team.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";

@Component({
  selector: 'teamCard',
  templateUrl: './teamCards.component.html',
  styleUrl: './teamCards.component.css'
})
export class TeamCardsComponent {
  team: Team[] = [];
  constructor(private teamsService: TeamService) {

  }
  ngOnInit(): void {
    console.log('Making request to get teams...');
    this.teamsService.getTeams().pipe(
      catchError(error => {
        console.error('Error occurred while fetching teams:', error);
        return throwError(error);
      })
    ).subscribe({
      next: (team) => {
        console.log('Received teams data:', team);
        this.team = team
      },
      error: (error) => {
        console.error('Error occurred while fetching teams:', error);
      }
    });
  }





}