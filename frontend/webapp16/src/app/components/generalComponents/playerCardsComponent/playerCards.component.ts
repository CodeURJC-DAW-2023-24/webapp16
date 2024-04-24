import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Player} from "../../../models/player.model";
import {PlayerService} from "../../../services/player.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {PaginationService} from "../../../services/pagination.service";
import {Router} from "@angular/router";

@Component({
  selector: 'playerCard',
  templateUrl: './playerCards.component.html',
  styleUrl: './playerCards.component.css'
})
export class PlayerCardsComponent implements OnInit{
  @Input() playerData: any;
  @Output() hasMoreDataChange = new EventEmitter<boolean>();
  page: number = 0;
  hasMoreData: boolean = true;

  constructor(private playersService: PlayerService, private paginationService: PaginationService, private router: Router) {
    this.paginationService.currentPage.subscribe(page => {
      this.page = page;
      this.loadMorePlayers();
    });
  }

  ngOnInit(): void {
  }

  loadMorePlayers(): void {
    this.playersService.getPlayers(this.page).pipe(
      catchError(error => {
        const errorCode = error.status;
        this.router.navigate(['/error'], { state: { errorCode: errorCode } });
        //console.error('Error occurred while fetching teams:', error);
        return throwError(error);
      })
    ).subscribe({
      next: (players) => {
        if (players.length === 0) {
          this.hasMoreData = false;
          console.log('No more players. hasMoreData:', this.hasMoreData); // Log the value of hasMoreData
          return;
        }
        // If playerData is not initialized, initialize it with the players from the first page
        if (!this.playerData) {
          this.playerData = players;
        } else {
          // If playerData is already initialized, add the new players to the end of the existing players
          this.playerData = [...this.playerData, ...players];
        }
        console.log('Page incremented. Current page:', this.page); // Log the current page
        this.page += 1;
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
