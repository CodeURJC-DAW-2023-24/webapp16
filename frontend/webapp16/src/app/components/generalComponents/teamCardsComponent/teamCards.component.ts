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
      next: (teams) => {
        console.log('Received teams data:', teams);
        this.teamData = teams.map(team => {
          // Add base64 image prefix if not already present
          if (!team.imagePath.startsWith('data:image')) {
            team.imagePath = 'data:image/jpeg;base64,' + team.imagePath;
          }
          return team;
        });
      },
      error: (error) => {
        console.error('Error occurred while fetching teams:', error);
      }
    });
  }else {
    // Add base64 image prefix if not already present for each team in the array teamData
this.teamData = this.teamData.map((team: any) => {
  if (!team.imagePath.startsWith('data:image')) {
    team.imagePath = 'data:image/jpeg;base64,' + team.imagePath;
  }
  return team;
});


  }
}





}
