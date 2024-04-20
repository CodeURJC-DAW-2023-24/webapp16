import {Team} from "./team.model";

export interface Player {
  id: number;
  name: string;
  lastName: string;
  age: string;
  jerseyNumber: number;
  nationality: string;
  goals: number;
  position: string;
  weight: string;
  height: string;
  team: Team;
}
//Path: frontend/webapp16/src/app/models/player.model.ts
