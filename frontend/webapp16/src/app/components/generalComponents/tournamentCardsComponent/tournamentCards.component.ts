import {Component, Input, OnInit} from '@angular/core';
import {Tournament} from "../../../models/tournament.model";
import {TournamentService} from "../../../services/tournament.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'tournamentCard',
  templateUrl: './tournamentCards.component.html',
  styleUrl: './tournamentCards.component.css'
})
export class TournamentCardsComponent implements OnInit{
  @Input() tournamentData: any;
  constructor(private tournamentService: TournamentService, private router: Router) {

  }
  ngOnInit(): void {
    if (!this.tournamentData || this.tournamentData.length === 0) {
      console.log('Making request to get tournaments...');
      this.tournamentService.getTournament().pipe(
        catchError(error => {
          const errorCode = error.status;
          this.router.navigate(['/error'], { state: { errorCode: errorCode } });
          //console.error('Error occurred while fetching tournaments:', error);
          return throwError(error);
        })
      ).subscribe({
        next: (tournament) => {
          console.log('Received tournaments data:', tournament);
          this.tournamentData = tournament;
        },
        error: (error) => {
          const errorCode = error.status;
          this.router.navigate(['/error'], { state: { errorCode: errorCode } });
          return throwError(error);
          //console.error('Error occurred while fetching tournaments:', error);
        }
      });
    }else {
      // Add base64 image prefix if not already present for each team in the array teamData
      this.tournamentData = this.tournamentData.map((tournament: any) => {
        if (!tournament.tournamentImagePath.startsWith('data:image')) {
          tournament.tournamentImagePath = 'data:image/jpeg;base64,' + tournament.tournamentImagePath;
        }
        return tournament;
      });


    }
  }





}
