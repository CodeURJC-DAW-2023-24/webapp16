import { Component } from '@angular/core';
import { PlayerService } from '../../../services/player.service';
import {Player} from "../../../models/player.model";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
@Component({
  selector: 'player',
  templateUrl: './player.component.html',
  styleUrl: './player.component.css'
})
export class PlayerComponent {

}
