import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TeamService} from "../../../services/team.service";
import {Player} from "../../../models/player.model";

@Component({
  selector: 'app-teamInfo',
  templateUrl: './teamInfo.component.html',
  styleUrls: ['./teamInfo.component.css']
})
export class TeamInfoComponent implements OnInit {

  team: any;
  players: Player [] = [];

  constructor(private teamService: TeamService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.teamService.getTeam(parseInt(id)).subscribe(data => {
        this.team = data;
        this.players = this.team.players;
      });
    }
  }
}
