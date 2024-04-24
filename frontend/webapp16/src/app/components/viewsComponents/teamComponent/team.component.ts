import {Component, OnInit} from '@angular/core';
import { TeamService } from '../../../services/team.service';
import {Team} from "../../../models/team.model";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
import {PaginationService} from "../../../services/pagination.service";

@Component({
  selector: 'team',
  templateUrl: './team.component.html',
  styleUrl: './team.component.css'
})

export class TeamComponent implements OnInit {
  hasMoreData: boolean = true;
  loadMoreButtonText: string = 'Load more teams'; // Add this line

  constructor(private paginationService: PaginationService,private titleService: Title) {
  }

  ngOnInit(): void {
    this.titleService.setTitle('Teams');
  }

  loadMoreTeams(): void {
    console.log('loadMoreTeams called. Incrementing page.');
    this.paginationService.incrementPage();
  }

  handleHasMoreDataChange(newHasMoreData: boolean): void {
    this.hasMoreData = newHasMoreData;
    console.log('hasMoreData changed. New value:', newHasMoreData);
    this.loadMoreButtonText = 'No more teams to load';
    if (!this.hasMoreData) {
      this.loadMoreButtonText = 'No more teams to load'; // Change the button text when there are no more teams to load
    }
  }
}
