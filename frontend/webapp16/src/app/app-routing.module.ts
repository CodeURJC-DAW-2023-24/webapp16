import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayersStatisticsComponent } from './components/pageComponents/playerStatistics/playersStatistics.component';
import { LoginComponent } from './components/viewsComponents/loginComponent/login.component';
import { LoginErrorComponent } from './components/viewsComponents/loginErrorComponent/loginError.component';
import {TeamsStatisticsComponent} from "./components/pageComponents/teamStatistics/teamStatistics.component";
import { FillMatchReportComponent } from './components/viewsComponents/fillMatchReportComponent/fillMatchReport.component';
import { ShowReportComponent } from './components/viewsComponents/showReportComponent/showReport.component';


const routes: Routes = [
 // { path: '', component: TournmamentComponent },
  { path: 'new', component: PlayersStatisticsComponent },
  { path: 'new/statistics/players', component: PlayersStatisticsComponent },
  { path: 'new/statistics/teams', component: TeamsStatisticsComponent },
  { path: 'new/login', component: LoginComponent },
  { path: 'new/login-error', component: LoginErrorComponent },
  { path: 'new/fill-match-report', component: FillMatchReportComponent },
  { path: 'new/show-report', component: ShowReportComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
