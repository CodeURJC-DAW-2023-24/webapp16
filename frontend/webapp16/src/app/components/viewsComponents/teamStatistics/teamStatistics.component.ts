// teamsStatistics.component.ts

import { Component, OnInit } from '@angular/core';
import { Chart, LineController, LineElement, PointElement, LinearScale, CategoryScale } from 'chart.js';
import { Team } from '../../../models/team.model';
import { TeamService } from '../../../services/team.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {Title} from "@angular/platform-browser";
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

Chart.register(LineController, LineElement,PointElement, LinearScale, CategoryScale);

@Component({
  selector: 'app-teamsStatistics',
  templateUrl: './teamsStatistics.component.html',
  styleUrls: ['./teamsStatistics.component.css']
})
export class TeamsStatisticsComponent implements OnInit {
  teams: Team[] = [];
  constructor(private teamService: TeamService, private titleService: Title, private router: Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Teams Statistics');
    //console.log('Making request to get top teams...');
    this.teamService.getTopTeams().pipe(
      catchError(error => {
        const errorCode = error.status;
        this.router.navigate(['/error'], { state: { errorCode: errorCode } });
        //console.error('Error occurred while fetching teams:', error);
        return throwError(error);
      })
    ).subscribe({
      next: (teams) => {
        //console.log('Received players data:', teams);
        this.teams = teams;
        this.generateChart();
      },
      error: (error) => {
        const errorCode = error.status;
        this.router.navigate(['/error'], { state: { errorCode: errorCode } });
        return throwError(error);
        //console.error('Error occurred while fetching players:', error);
      }
    });
  }

  generateChart(): void {
    //console.log('Generating chart with teams data:', this.teams);
    const teams = this.teams;

    const teamNames = teams.map((team: Team) => team.name);
    const wins = teams.map((team: Team) => team.wins);

    //console.log('Team names for chart:', teamNames);
    //console.log('Wins for chart:', wins);

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
    //console.log('Chart generated:', myChart);
  }
}
