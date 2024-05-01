import {Team} from "./team.model";
import {Tournament} from "./tournament.model";

export interface Match {
  id: number;
  localTeam: Team;
  visitingTeam: Team;
  localGoals: number;
  visitingGoals: number;
  round: number;
  tournament: Tournament;
}

