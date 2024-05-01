import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FooterComponent} from "./components/generalComponents/footerComponent/footer.component";
import {HeaderComponent} from "./components/generalComponents/headerComponent/header.component";
import {BannerComponent} from "./components/generalComponents/bannerComponent/banner.component";
import {PlayersStatisticsComponent} from "./components/viewsComponents/playerStatistics/playersStatistics.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LoginComponent} from "./components/viewsComponents/loginComponent/login.component";
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginErrorComponent } from "./components/viewsComponents/loginErrorComponent/loginError.component";
import {TeamsStatisticsComponent} from "./components/viewsComponents/teamStatistics/teamStatistics.component";
import { FillMatchReportComponent } from './components/viewsComponents/fillMatchReportComponent/fillMatchReport.component';
import {ReactiveFormsModule} from "@angular/forms";
import { ShowReportComponent } from './components/viewsComponents/showReportComponent/showReport.component';
import { ErrorComponent } from "./components/viewsComponents/errorComponent/error.component";
import {AboutUsComponent} from "./components/viewsComponents/aboutUsComponent/aboutUs.component";
import {ProfileComponent} from "./components/viewsComponents/profileComponent/profile.component";
import {PlayerInfoComponent} from "./components/viewsComponents/playerInfoComponent/playerInfo.component";
import {PlayerComponent} from "./components/viewsComponents/playerComponent/player.component";
import {PlayerCardsComponent} from "./components/generalComponents/playerCardsComponent/playerCards.component";
import {TeamComponent} from "./components/viewsComponents/teamComponent/team.component";
import {TeamCardsComponent} from "./components/generalComponents/teamCardsComponent/teamCards.component";
import {TournamentComponent} from "./components/viewsComponents/tournamentComponent/tournament.component";
import {TournamentCardsComponent} from "./components/generalComponents/tournamentCardsComponent/tournamentCards.component";
import {TeamInfoComponent} from "./components/viewsComponents/teamInfoComponent/teamInfo.component";
import {SearchComponent} from "./components/viewsComponents/searchComponent/search.component";
import {AuthInterceptor} from "./services/auth.interceptor";
import {TournamentBracketComponent} from "./components/viewsComponents/tournamentBracketComponent/tournamentBracket.component";
import {MatchInfoComponent} from "./components/viewsComponents/matchInfoComponent/matchInfo.component";


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    BannerComponent,
    PlayersStatisticsComponent,
    LoginComponent,
    LoginErrorComponent,
    TeamsStatisticsComponent,
    FillMatchReportComponent,
    ShowReportComponent,
    ErrorComponent,
    AboutUsComponent,
    ProfileComponent,
    PlayerComponent,
    PlayerInfoComponent,
    PlayerCardsComponent,
    TeamComponent,
    TeamCardsComponent,
    TournamentComponent,
    TournamentCardsComponent,
    TeamInfoComponent,
    SearchComponent,
    TournamentBracketComponent,
    MatchInfoComponent



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    NgbNavModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
