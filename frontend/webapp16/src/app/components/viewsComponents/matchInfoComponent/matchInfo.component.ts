import {Component, OnInit} from '@angular/core';
import {MatchService} from "../../../services/match.service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {Match} from "../../../models/match.model";
@Component({
  selector: 'matchInfo',
  templateUrl: './matchInfo.component.html',
  styleUrl: './matchInfo.component.css'
})
export class MatchInfoComponent implements OnInit{
  // Match atributte
  match: any;
  matchId: any;

  constructor(private titleService: Title, private route: ActivatedRoute, private router: Router, private matchService: MatchService) {
  }
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id'); //get match ID from URL
    this.matchId= id;

    this.titleService.setTitle('matchInfo/' + id);
    if (id != null) {
      this.matchService.getMatchesById(id).subscribe({
        next : (match) => {
          this.match = match;
          console.log("This is the match object: " + JSON.stringify(match));        },
        error: (error) => {
          const errorCode = error.status;
          this.router.navigate(['/error'], { state: { errorCode: errorCode } });
          return throwError(error);
        }
      }
      );


    }
  }
}

