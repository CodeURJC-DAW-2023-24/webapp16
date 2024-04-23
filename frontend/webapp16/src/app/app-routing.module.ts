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
import {TournamentComponent} from "./components/viewsComponents/TournamentComponent/tournament.component";
import {PlayerComponent} from "./components/viewsComponents/playerComponent/player.component";
import {TeamComponent} from "./components/viewsComponents/teamComponent/team.component";
import {PlayerInfoComponent} from "./components/viewsComponents/playerInfoComponent/playerInfo.component";
import {TeamInfoComponent} from "./components/viewsComponents/teamInfoComponent/teamInfo.component";
import {SearchComponent} from "./components/viewsComponents/searchComponent/search.component";


const routes: Routes = [
  { path: 'aboutUs', component: AboutUsComponent },
  { path: '', component: TournamentComponent },
  { path: 'statistics/players', component: PlayersStatisticsComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'statistics/teams', component: TeamsStatisticsComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'players', component: PlayerComponent },
  { path: 'teams', component: TeamComponent },
  { path: 'login', component: LoginComponent },
  { path: 'login-error', component: LoginErrorComponent },
  { path: 'fill-match-report', component: FillMatchReportComponent, canActivate: [AuthGuard], data: { expectedRole: 'ADMIN' } },
  { path: 'show-report', component: ShowReportComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'error', component: ErrorComponent },
  { path: 'profile', component: ProfileComponent }, //, canActivate: [AuthGuard], data: { expectedRole: 'USER' }
  { path: 'playerInfo/:id', component: PlayerInfoComponent },
  { path: 'teamInfo/:id', component: TeamInfoComponent },
  { path: 'search', component: SearchComponent },
  { path: 'logout', component: LoginComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
