import {Component, OnInit} from '@angular/core';
import { PlayerService } from '../../../services/player.service';
import {Player} from "../../../models/player.model";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
import {PaginationService} from "../../../services/pagination.service";
@Component({
  selector: 'player',
  templateUrl: './player.component.html',
  styleUrl: './player.component.css'
})
export class PlayerComponent implements OnInit {
  hasMoreData: boolean = true;
  loadMoreButtonText: string = 'Load more players'; // Add this line

  constructor(private paginationService: PaginationService,private titleService: Title) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Players');
  }

  loadMorePlayers(): void {
    //console.log('loadMorePlayers called. Incrementing page.');
    this.paginationService.incrementPage();
  }

  handleHasMoreDataChange(newHasMoreData: boolean): void {
    this.hasMoreData = newHasMoreData;
    //console.log('hasMoreData changed. New value:', newHasMoreData);
    this.loadMoreButtonText = 'No more players to load';
    if (!this.hasMoreData) {
      this.loadMoreButtonText = 'No more players to load'; // Change the button text when there are no more players to load
    }
  }
}
