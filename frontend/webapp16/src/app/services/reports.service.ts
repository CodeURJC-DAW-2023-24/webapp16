import {Component, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, BehaviorSubject, throwError, of} from 'rxjs';
import {API_URL} from "../../config";
import {Router} from "@angular/router";
import {Match} from "../models/match.model";
import {catchError, tap} from "rxjs/operators";
import {Team} from "../models/team.model";
import {Report} from "../models/report.model";
import {MatchService} from "./match.service";
import {TeamService} from "./team.service";

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  private apiUrl = `${API_URL}/matches`;

  championsList: any;
  reyList: any;


  constructor(private http: HttpClient, private router: Router, private  matchService:MatchService, private teamService: TeamService) {
  }


  getReportById(id: string): Observable<Report | null> {
    return this.http.get<Report>(`${API_URL}/reports/matchReport/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        if (err.status === 404) {
          // If the error is 404, return an Observable of null
          return of(null);
        } else {
          // For other errors, navigate to the error page
          this.router.navigate(['/error']);
          return throwError(err);
        }
      })
    );
  }

  postMatchReport(date: any, time: any, matchOfficials: any, localTeamGoals: any, visitingTeamGoals: any, observations: any, match: any): Observable<any> {
    return this.http.post(`${API_URL}/reports/`,
      {date, time, matchOfficials, localTeamGoals, visitingTeamGoals, observations, match},
      {observe: 'response', withCredentials: true}).pipe(tap(response => {
      if (response.status === 200) {
        console.log("update rounds") // Update loggedIn status
      }
      this.router.navigate(['/']);
    }));
  }

  postMatchReport2(report: any): Observable<any> {
    return this.http.post(`${API_URL}/reports/`,
      report,
      {observe: 'response', withCredentials: true}).pipe(tap(response => {
      if (response.status === 201) {
        console.log("update rounds") // Update loggedIn status

        this.handleReport(report);
        this.updateMatch(report);
        this.updateStatistics(report);
      }
      this.router.navigate(['/']);
    }));
  }


private winners: Map<number, Team[]> = new Map();

handleReport(report: Report) {
  const tournamentId = report.match.tournament.id;
  const winningTeam = report.localTeamGoals > report.visitingTeamGoals ? report.match.localTeam : report.match.visitingTeam;

  if (!this.winners.has(tournamentId)) {
    this.winners.set(tournamentId, []);
  }

  const winners = this.winners.get(tournamentId);
  if (winners) {
    winners.push(winningTeam);

    if (winners.length === 2) {
      const match = new Match(winners[0], winners[1], 0, 0, report.match.round+1, report.match.tournament);
     this.matchService.postMatch(match).pipe(
     catchError(error => {
    console.error('Error occurred while posting match:', error);
    return throwError(error);
    })
    ).subscribe(() => {
     this.winners.set(tournamentId, []);
    });

    }else if (winners.length === 1 && report.match.round === 3) {
      const match = new Match(winners[0], winners[1], 0, 0, report.match.round+1, report.match.tournament);
      this.matchService.postMatch(match).pipe(
        catchError(error => {
          console.error('Error occurred while posting match:', error);
          return throwError(error);
        })
      ).subscribe(() => {
        this.winners.set(tournamentId, []);
      });
    }
  }
  console.log("el map esta asi", this.winners);
}


updateMatch(report: Report) {
  const match = report.match;
  match.id = report.match.id;
  match.localGoals = report.localTeamGoals;
  match.visitingGoals = report.visitingTeamGoals;
  this.matchService.putMatch(match).pipe(
    catchError(error => {
      console.error('Error occurred while updating match:', error);
      return throwError(error);
    })
  ).subscribe(() => {
    console.log("Match updated");
  });
}

updateStatistics(report: Report) {
  const winningTeam = report.localTeamGoals > report.visitingTeamGoals ? report.match.localTeam : report.match.visitingTeam;
  const losingTeam = report.localTeamGoals > report.visitingTeamGoals ? report.match.visitingTeam : report.match.localTeam;

  winningTeam.wins++;
  losingTeam.loses++;

  // Actualizar los equipos en el servidor
  this.teamService.updateTeam(winningTeam).subscribe(
    () => {
      console.log("Winning team updated");
      console.log(winningTeam.wins);
    },
    error => {
      console.error('Error occurred while updating winning team:', error);
    }
  );
  this.teamService.updateTeam(losingTeam).subscribe(
    () => {
      console.log("Winning team updated");
      console.log(winningTeam.wins);
    },
    error => {
      console.error('Error occurred while updating winning team:', error);
    }
  );


}

}









