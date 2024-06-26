// app-routing.module.ts

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './services/auth.guard';
import { PlayersStatisticsComponent } from './components/viewsComponents/playerStatistics/playersStatistics.component';
import { LoginComponent } from './components/viewsComponents/loginComponent/login.component';
import { LoginErrorComponent } from './components/viewsComponents/loginErrorComponent/loginError.component';
import {TeamsStatisticsComponent} from "./components/viewsComponents/teamStatistics/teamStatistics.component";
import { FillMatchReportComponent } from './components/viewsComponents/fillMatchReportComponent/fillMatchReport.component';
import { ShowReportComponent } from './components/viewsComponents/showReportComponent/showReport.component';
import { ErrorComponent } from './components/viewsComponents/errorComponent/error.component';
import {AboutUsComponent} from "./components/viewsComponents/aboutUsComponent/aboutUs.component";
import {ProfileComponent} from "./components/viewsComponents/profileComponent/profile.component";
import {TournamentComponent} from "./components/viewsComponents/tournamentComponent/tournament.component";
import {PlayerComponent} from "./components/viewsComponents/playerComponent/player.component";
import {TeamComponent} from "./components/viewsComponents/teamComponent/team.component";
import {PlayerInfoComponent} from "./components/viewsComponents/playerInfoComponent/playerInfo.component";
import {TeamInfoComponent} from "./components/viewsComponents/teamInfoComponent/teamInfo.component";
import {SearchComponent} from "./components/viewsComponents/searchComponent/search.component";
import {TournamentBracketComponent} from "./components/viewsComponents/tournamentBracketComponent/tournamentBracket.component";
import {MatchInfoComponent} from "./components/viewsComponents/matchInfoComponent/matchInfo.component";
import {NewTeamComponent} from "./components/viewsComponents/newTeamComponent/newTeam.component";
import {NewTournamentComponent} from "./components/viewsComponents/newTournamentComponent/newTournament.component";


const routes: Routes = [
  { path: 'aboutUs', component: AboutUsComponent },
  { path: '', component: TournamentComponent },
  { path: 'tournament/:id', component: TournamentBracketComponent },
  {path: 'matchInfo/:id', component: MatchInfoComponent},
  { path: 'statistics/players', component: PlayersStatisticsComponent},
  { path: 'statistics/teams', component: TeamsStatisticsComponent},
  { path: 'players', component: PlayerComponent },
  { path: 'teams', component: TeamComponent },
  { path: 'login', component: LoginComponent },
  { path: 'login-error', component: LoginErrorComponent },
  { path: 'fillMatchReport/:id', component: FillMatchReportComponent, canActivate: [AuthGuard], data: { expectedRole: 'ADMIN' } },
  { path: 'report/:id', component: ShowReportComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'error', component: ErrorComponent },
  { path: 'profile', component: ProfileComponent,  canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'playerInfo/:id', component: PlayerInfoComponent },
  { path: 'teamInfo/:id', component: TeamInfoComponent },
  { path: 'search', component: SearchComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' }  },
  { path: 'logout', component: LoginComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' }   },
  { path: 'addNewTeamToTournament', component: NewTeamComponent, canActivate: [AuthGuard], data: { expectedRole: 'ADMIN' }},
  { path: 'addNewTournament', component: NewTournamentComponent, canActivate: [AuthGuard], data: { expectedRole: 'ADMIN' }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
