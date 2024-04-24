import {Component, Input, OnInit} from '@angular/core';
import {Player} from "../../../models/player.model";
import {PlayerService} from "../../../services/player.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'playerCard',
  templateUrl: './playerCards.component.html',
  styleUrl: './playerCards.component.css'
})
export class PlayerCardsComponent  implements  OnInit{
  @Input() playerData: any;
  constructor(private playerService: PlayerService, private router: Router) {

  }
ngOnInit(): void {
    console.log("ACTUAL DATA IS: ", this.playerData)
  if (!this.playerData || this.playerData.length === 0) {
    console.log('Making request to get players...');
    this.playerService.getPlayers().pipe(
      catchError(error => {
        const errorCode = error.status;
        this.router.navigate(['/error'], { state: { errorCode: errorCode } });
        //console.error('Error occurred while fetching teams:', error);
        return throwError(error);
      })
    ).subscribe({
      next: (player) => {
        console.log('Received players data:', player);
        this.playerData = player;
      },
      error: (error) => {
        const errorCode = error.status;
        this.router.navigate(['/error'], { state: { errorCode: errorCode } });
        return throwError(error);
        //console.error('Error occurred while fetching players:', error);
      }
    });
  }
}
}
