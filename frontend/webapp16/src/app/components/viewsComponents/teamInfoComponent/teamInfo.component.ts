import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TeamService} from "../../../services/team.service";
import {Player} from "../../../models/player.model";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-teamInfo',
  templateUrl: './teamInfo.component.html',
  styleUrls: ['./teamInfo.component.css']
})
export class TeamInfoComponent implements OnInit {

  team: any;
  players: Player [] = [];

  constructor(private teamService: TeamService, private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.teamService.getTeam(id).subscribe(team => {
        this.team = team;
        this.titleService.setTitle(this.team.name + ' Info');
        if (this.titleService.getTitle() != null){
          // @ts-ignore
          document.getElementById("pageTitle").innerHTML =this.titleService.getTitle();
        }
      });
      this.teamService.getTeamPlayers(id).subscribe(players => {
        this.players = players;
      });
    }
  }
}
