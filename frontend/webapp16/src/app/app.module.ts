import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FooterComponent} from "./components/generalComponents/footerComponent/footer.component";
import {HeaderComponent} from "./components/generalComponents/headerComponent/header.component";
import {PlayersStatisticsComponent} from "./components/pageComponents/playerStatistics/playersStatistics.component";
import {HttpClientModule} from "@angular/common/http";
import {LoginComponent} from "./components/pageComponents/login/login.component";

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    PlayersStatisticsComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
