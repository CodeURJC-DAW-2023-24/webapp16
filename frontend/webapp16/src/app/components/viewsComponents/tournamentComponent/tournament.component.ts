import {Component, OnInit} from '@angular/core';
import { TournamentService } from '../../../services/tournament.service';
import {Tournament} from "../../../models/tournament.model";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
import {AuthService} from "../../../services/auth.service";
@Component({
  selector: 'tournament',
  templateUrl: './tournament.component.html',
  styleUrl: './tournament.component.css'
})
export class TournamentComponent implements OnInit{
  isAdmin$: Observable<boolean>;
  constructor(public authService: AuthService, private titleService: Title) {
    this.isAdmin$ = this.authService.isAdmin();
  }
  ngOnInit(): void {
    this.isAdmin$ = this.authService.isAdmin();
    this.titleService.setTitle('Tournament');
  }
}

