import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import {ActivatedRoute, Router} from "@angular/router";
import {MatchService} from "../../../services/match.service";
import {Match} from "../../../models/match.model";
import {throwError} from "rxjs";

@Component({
  selector: 'tournamentBracket',
  templateUrl: './tournamentBracket.component.html',
  styleUrls: ['./tournamentBracket.component.css']
})
export class TournamentBracketComponent implements OnInit {
  matches: Match[] = [];
  matchesRound1: Match[] = [];
  matchesRound2: Match[] = [];
  matchesRound3: Match[] = [];
  matchesRound4: Match[] = [];
  constructor(private titleService: Title, private route:ActivatedRoute, private matchService: MatchService , private router:Router) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id'); //get Touranment ID from URL
    this.titleService.setTitle('Tournament/' + id);

    if (id != null) {
      this.matchService.getMatches(id).subscribe({
          next : (matches) => {
            if (matches.length === 0) {
              console.log('No matches found');
            this.router.navigate(['/error'], { state: { errorCode: 404 } });} // Redirect to error page
           this.matches = matches;
           this.getMatchesByRound(this.matches);
           console.log(matches)
          },
          error: (error) => {
            const errorCode = error.status;
            this.router.navigate(['/error'], { state: { errorCode: errorCode } });
            return throwError(error);
            //console.error('Error occurred while fetching bracket:', error);
          }


    }
    );


  }
}


getMatchesByRound(matches: Match[]) {

      // Separate round by round
      for (let match of matches) {
        switch (match.round) {
          case 1:
            this.matchesRound1.push(match);
            break;
          case 2:
            this.matchesRound2.push(match);
            break;
          case 3:
            this.matchesRound3.push(match);
            break;
          case 4:
            this.matchesRound4.push(match);
            break;

        }
      }

    }

}
