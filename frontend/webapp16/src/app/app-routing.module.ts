import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayersStatisticsComponent } from './components/pageComponents/playerStatistics/playersStatistics.component';
import { LoginComponent } from './components/viewsComponents/loginComponent/login.component';
import { LoginErrorComponent } from './components/viewsComponents/loginErrorComponent/loginError.component';
import {TeamsStatisticsComponent} from "./components/pageComponents/teamStatistics/teamStatistics.component";


const routes: Routes = [
  { path: 'new', component: PlayersStatisticsComponent },
  { path: 'new/statistics/players', component: PlayersStatisticsComponent },
  { path: 'new/statistics/teams', component: TeamsStatisticsComponent },
  { path: 'new/login', component: LoginComponent },
  { path: 'new/login-error', component: LoginErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
