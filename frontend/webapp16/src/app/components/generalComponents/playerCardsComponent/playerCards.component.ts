import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Player} from "../../../models/player.model";
import {PlayerService} from "../../../services/player.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {PaginationService} from "../../../services/pagination.service";
import {Router} from "@angular/router";
import {SearchService} from "../../../services/search.service";

@Component({
  selector: 'playerCard',
  templateUrl: './playerCards.component.html',
  styleUrl: './playerCards.component.css'
})
export class PlayerCardsComponent implements OnInit{
  @Input() playerData: any;
  @Output() hasMoreDataChange = new EventEmitter<boolean>();
  @Input() loadDefaultPlayers: boolean = true;
  page: number = 0;
  hasMoreData: boolean = true;
  isSearchActive: boolean = false;

  constructor(private playersService: PlayerService, private paginationService: PaginationService, private router: Router, private searchService: SearchService) {
    this.searchService.getIsSearchActive().subscribe(isActive => {
      this.isSearchActive = isActive;
    });

    this.paginationService.currentPage.subscribe(page => {
      this.page = page;
      console.log('currentPage:', this.page);
      if (!this.isSearchActive) {
        console.log('Constructor Search active', this.isSearchActive);
        this.loadMorePlayers();
      }
    });
  }

  ngOnInit(): void {
    this.page= 0;
    console.log('ngOnInit page:', this.page);
    this.isSearchActive = this.router.url.startsWith('/search');
  }
  ngOnDestroy(): void {
    this.paginationService.resetPage();
  }
  loadMorePlayers(): void {
    if (!this.isSearchActive) {
      console.log('LoadMore Search active', this.isSearchActive);
      console.log('loadMorePlayers called');
      this.playersService.getPlayers(this.page).pipe(
        catchError(error => {
          const errorCode = error.status;
          this.router.navigate(['/error'], { state: { errorCode: errorCode } });
          return throwError(error);
        })
      ).subscribe({
        next: (players) => {
          if (players.length === 0) {
            this.hasMoreData = false;
            this.hasMoreDataChange.emit(this.hasMoreData);
            return;
          }
          if (!this.playerData) {
            this.playerData = players;
          } else {
            this.playerData = [...this.playerData, ...players];
          }
          this.page += 1;
        },
        error: (error) => {
          const errorCode = error.status;
          this.router.navigate(['/error'], { state: { errorCode: errorCode } });
          return throwError(error);
        }
      });
    }
  }
}
