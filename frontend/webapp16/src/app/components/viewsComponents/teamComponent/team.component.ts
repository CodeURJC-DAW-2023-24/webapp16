import { Component } from '@angular/core';
import { TeamService } from '../../../services/team.service';
import {Team} from "../../../models/team.model";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
@Component({
  selector: 'team',
  templateUrl: './team.component.html',
  styleUrl: './team.component.css'
})
export class TeamComponent {

}
