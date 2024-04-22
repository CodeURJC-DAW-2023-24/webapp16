import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../../services/player.service';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-playerInfo',
  templateUrl: './playerInfo.component.html',
  styleUrls: ['./playerInfo.component.css']
})
export class PlayerInfoComponent implements OnInit {

  player: any;

  constructor(private playerService: PlayerService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.playerService.getPlayer(parseInt(id)).subscribe(data => {
        this.player = data;
    });
    }
  }
}
