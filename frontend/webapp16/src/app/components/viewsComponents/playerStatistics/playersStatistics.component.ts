// playersStatistics.component.ts

import { Component, OnInit } from '@angular/core';
import { Chart, BarController, BarElement, LinearScale, CategoryScale } from 'chart.js';
import { Player } from '../../../models/player.model';
import { PlayerService } from '../../../services/player.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {Title} from "@angular/platform-browser";
import { Router } from '@angular/router';

Chart.register(BarController, BarElement, LinearScale, CategoryScale);

@Component({
  selector: 'app-playersStatistics',
  templateUrl: './playersStatistics.component.html',
  styleUrls: ['./playersStatistics.component.css']
})
export class PlayersStatisticsComponent implements OnInit {
  players: Player[] = [];
  constructor(private playerService: PlayerService, private titleService: Title, private router: Router) { }

  ngOnInit(): void {
    this.titleService.setTitle('Players Statistics');
    //console.log('Making request to get top players...');
    this.playerService.getTopPlayers().pipe(
     catchError(error => {
        const errorCode = error.status;
        this.router.navigate(['/error'], { state: { errorCode: errorCode } });
        //console.error('Error occurred while fetching players:', error);
        return throwError(error);
      })
    ).subscribe({
      next: (players) => {
        //console.log('Received players data:', players);
        this.players = players;
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
    console.log('Generating chart with players data:', this.players);
    const players = this.players;

    const playerNames = players.map((player: Player) => `${player.name} ${player.lastName}`);
    const goals = players.map((player: Player) => player.goals);

    console.log('Player names for chart:', playerNames);
    console.log('Goals for chart:', goals);

    const ctx = document.getElementById('myChart') as HTMLCanvasElement;
    const myChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: playerNames,
        datasets: [{
          label: 'Players Goals',
          data: goals,
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
