import {Component, OnInit} from '@angular/core';
import { TournamentService } from '../../../services/tournament.service';
import {Tournament} from "../../../models/tournament.model";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
@Component({
  selector: 'tournament',
  templateUrl: './tournament.component.html',
  styleUrl: './tournament.component.css'
})
export class TournamentComponent implements OnInit{
  constructor(private titleService: Title) {
  }
  ngOnInit(): void {
    this.titleService.setTitle(' ');
  }
}

