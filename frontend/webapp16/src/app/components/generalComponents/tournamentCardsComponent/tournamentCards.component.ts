import { Component } from '@angular/core';
import {Tournament} from "../../../models/tournament.model";
import {TournamentService} from "../../../services/tournament.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";

@Component({
  selector: 'tournamentCard',
  templateUrl: './tournamentCards.component.html',
  styleUrl: './tournamentCards.component.css'
})
export class TournamentCardsComponent {
  tournament: Tournament[] = [];
  constructor(private tournamentService: TournamentService) {

  }
  ngOnInit(): void {
    console.log('Making request to get tournaments...');
    this.tournamentService.getTournament().pipe(
      catchError(error => {
        console.error('Error occurred while fetching tournaments:', error);
        return throwError(error);
      })
    ).subscribe({
      next: (tournament) => {
        console.log('Received tournaments data:', tournament);
        this.tournament = tournament;
      },
      error: (error) => {
        console.error('Error occurred while fetching tournaments:', error);
      }
    });
  }





}
