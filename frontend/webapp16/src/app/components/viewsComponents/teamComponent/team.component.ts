import {Component, OnInit} from '@angular/core';
import { TeamService } from '../../../services/team.service';
import {Team} from "../../../models/team.model";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
@Component({
  selector: 'team',
  templateUrl: './team.component.html',
  styleUrl: './team.component.css'
})

export class TeamComponent implements OnInit {
  constructor(private titleService: Title) {
  }
  ngOnInit(): void {
    this.titleService.setTitle('Teams');
  }
}
