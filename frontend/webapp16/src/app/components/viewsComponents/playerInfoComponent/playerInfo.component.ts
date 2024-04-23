import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../../services/player.service';
import {ActivatedRoute} from "@angular/router";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-playerInfo',
  templateUrl: './playerInfo.component.html',
  styleUrls: ['./playerInfo.component.css']
})
export class PlayerInfoComponent implements OnInit {

  player: any;

  constructor(private playerService: PlayerService, private route: ActivatedRoute, private titleService: Title) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.playerService.getPlayer(parseInt(id)).subscribe(data => {
        this.player = data;
        this.titleService.setTitle(this.player.name + ' ' + this.player.lastName + ' Info');
    });
    }
  }
}
