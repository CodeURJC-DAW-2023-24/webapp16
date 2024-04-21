import { Component } from '@angular/core';
import { TournamentService } from '../../../services/tournament.service';
import {Tournament} from "../../../models/tournament.model";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
@Component({
  selector: 'tournament',
  templateUrl: './tournament.component.html',
  styleUrl: './tournament.component.css'
})
export class TournamentComponent {

}
