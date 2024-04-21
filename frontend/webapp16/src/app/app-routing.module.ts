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
import {PlayersComponent} from "./components/viewsComponents/playersComponent/players.component";
import {TournamentComponent} from "./components/viewsComponents/TournamentComponent/tournament.component";

const routes: Routes = [
  { path: 'new/aboutUs', component: AboutUsComponent },
  { path: 'new', component: TournamentComponent, canActivate: [AuthGuard] },
  { path: 'new/statistics/players', component: PlayersStatisticsComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'new/statistics/teams', component: TeamsStatisticsComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },

  { path: 'new/login', component: LoginComponent },
  { path: 'new/login-error', component: LoginErrorComponent },
  { path: 'new/fill-match-report', component: FillMatchReportComponent, canActivate: [AuthGuard], data: { expectedRole: 'ADMIN' } },
  { path: 'new/show-report', component: ShowReportComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'new/error', component: ErrorComponent },
  { path: 'new/profile', component: ProfileComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } },
  { path: 'new/players', component: PlayersComponent, canActivate: [AuthGuard], data: { expectedRole: 'USER' } }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
