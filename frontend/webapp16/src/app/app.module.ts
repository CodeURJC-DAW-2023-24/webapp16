import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FooterComponent} from "./components/generalComponents/footerComponent/footer.component";
import {HeaderComponent} from "./components/generalComponents/headerComponent/header.component";
import {PlayersStatisticsComponent} from "./components/pageComponents/playerStatistics/playersStatistics.component";
import {HttpClientModule} from "@angular/common/http";
import {LoginComponent} from "./components/viewsComponents/loginComponent/login.component";
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginErrorComponent } from "./components/viewsComponents/loginErrorComponent/loginError.component";
import {TeamsStatisticsComponent} from "./components/pageComponents/teamStatistics/teamStatistics.component";
import { FillMatchReportComponent } from './components/viewsComponents/fillMatchReportComponent/fillMatchReport.component';
import {ReactiveFormsModule} from "@angular/forms";




@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    PlayersStatisticsComponent,
    LoginComponent,
    LoginErrorComponent,
    TeamsStatisticsComponent,
    FillMatchReportComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    NgbNavModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
