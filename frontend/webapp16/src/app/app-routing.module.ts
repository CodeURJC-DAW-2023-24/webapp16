import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayersStatisticsComponent } from './components/pageComponents/playerStatistics/playersStatistics.component';
import { LoginComponent } from './components/viewsComponents/loginComponent/login.component';


const routes: Routes = [
  { path: 'new', component: PlayersStatisticsComponent },
  { path: 'new/statistics/players', component: PlayersStatisticsComponent },
  { path: 'new/login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
