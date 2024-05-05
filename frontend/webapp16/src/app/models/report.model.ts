import {Match} from "./match.model";

export interface Report {
  id: number;
  date: string;
  time: string;
  matchOfficials: string;
  localTeamGoals: number;
  visitingTeamGoals: number;
  observations: string;
  match: Match; // Asegúrate de tener una clase o interfaz Matches en TypeScript
}
