import {Team} from "./team.model";
import {Tournament} from "./tournament.model";


export class Match {
  id?: number;
  localTeam: Team;
  visitingTeam: Team;
  localGoals?: number;
  visitingGoals?: number;
  round: number;
  tournament: Tournament;

  //empty constructor
  constructor(localTeam: Team, visitingTeam: Team, localGoals: number, visitingGoals: number, round: number, tournament: Tournament){
    this.localTeam = localTeam;
    this.visitingTeam = visitingTeam;
    this.localGoals = localGoals;
    this.visitingGoals = visitingGoals;
    this.round = round;
    this.tournament = tournament;
  }
}

