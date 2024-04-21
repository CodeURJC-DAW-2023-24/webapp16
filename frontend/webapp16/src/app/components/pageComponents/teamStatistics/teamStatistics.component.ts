// teamsStatistics.component.ts

import { Component, OnInit } from '@angular/core';
import { Chart, LineController, LineElement, PointElement, LinearScale, CategoryScale } from 'chart.js';
import { Team } from '../../../models/team.model';
import { TeamService } from '../../../services/team.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

Chart.register(LineController, LineElement,PointElement, LinearScale, CategoryScale);

@Component({
  selector: 'app-teamsStatistics',
  templateUrl: './teamsStatistics.component.html',
  styleUrls: ['./teamsStatistics.component.css']
})
export class TeamsStatisticsComponent implements OnInit {
  teams: Team[] = [];
  constructor(private teamService: TeamService) { }

  ngOnInit(): void {
    console.log('Making request to get top teams...');
    this.teamService.getTopTeams().pipe(
      catchError(error => {
        console.error('Error occurred while fetching teams:', error);
        return throwError(error);
      })
    ).subscribe(teams => {
      console.log('Received teams data:', teams);
      this.teams = teams;
      this.generateChart();
    }, error => {
      console.error('Error occurred while fetching teams:', error);
    });
  }

  generateChart(): void {
    console.log('Generating chart with teams data:', this.teams);
    const teams = this.teams;

    const teamNames = teams.map((team: Team) => team.name);
    const wins = teams.map((team: Team) => team.wins);

    console.log('Team names for chart:', teamNames);
    console.log('Wins for chart:', wins);

    const ctx = document.getElementById('myChart') as HTMLCanvasElement;
    const myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: teamNames,
        datasets: [{
          label: 'Teams Wins',
          data: wins,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
    console.log('Chart generated:', myChart);
  }
}