import {Component, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, BehaviorSubject, throwError} from 'rxjs';
import {API_URL} from "../../config";
import {SessionService} from "./session.service";
import {Router} from "@angular/router";
import {Match} from "../models/match.model";
import {catchError, tap} from "rxjs/operators";
import {TournamentService} from "./tournament.service";
import {Team} from "../models/team.model";
import {Report} from "../models/report.model";
import {ngbRunTransition} from "@ng-bootstrap/ng-bootstrap/util/transition/ngbTransition";
import {MatchService} from "./match.service";

@Injectable({
  providedIn: 'root'
})
export class ReportsService {
  private apiUrl = `${API_URL}/matches`;

  championsList: any;
  reyList: any;


  constructor(private http: HttpClient, private router: Router, private  matchService:MatchService) {
  }


  getReportById(id: string): Observable<Report> {
    return this.http.get<Report>(`${API_URL}/reports/matchReport/${id}`, {withCredentials: true}).pipe(
      catchError(err => {
        //console.error('Error occurred while fetching report:', err);
        this.router.navigate(['/error']);
        return new Observable<Report>();
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
        this.updateMatch(report);
        this.handleReport(report)
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

}









