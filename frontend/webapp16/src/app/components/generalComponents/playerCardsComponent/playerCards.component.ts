import { Component } from '@angular/core';
import {Player} from "../../../models/player.model";
import {PlayerService} from "../../../services/player.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";

@Component({
  selector: 'playerCard',
  templateUrl: './playerCards.component.html',
  styleUrl: './playerCards.component.css'
})
export class PlayerCardsComponent {
  player: Player[] = [];
  constructor(private playerService: PlayerService) {

  }
  ngOnInit(): void {
    console.log('Making request to get players...');
    this.playerService.getPlayers().pipe(
      catchError(error => {
        console.error('Error occurred while fetching players:', error);
        return throwError(error);
      })
    ).subscribe({
      next: (player) => {
        console.log('Received players data:', player);
        this.player = player;
      },
      error: (error) => {
        console.error('Error occurred while fetching players:', error);
      }
    });
  }
}
