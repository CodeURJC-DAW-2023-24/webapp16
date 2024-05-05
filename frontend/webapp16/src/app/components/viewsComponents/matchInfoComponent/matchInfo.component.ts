import {Component, OnInit} from '@angular/core';
import {MatchService} from "../../../services/match.service";
import {Observable, throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";
@Component({
  selector: 'matchInfo',
  templateUrl: './matchInfo.component.html',
  styleUrl: './matchInfo.component.css'
})
export class MatchInfoComponent implements OnInit{
  // Match atributte
  match: any;
  matchId: any;
  isAdmin$: Observable<boolean>;

  constructor(public authService: AuthService,private titleService: Title, private route: ActivatedRoute, private router: Router, private matchService: MatchService) {
    this.isAdmin$ = this.authService.isAdmin();
  }
  ngOnInit(): void {
    this.isAdmin$ = this.authService.isAdmin();
    const id = this.route.snapshot.paramMap.get('id'); //get match ID from URL
    this.matchId= id;

    this.titleService.setTitle('matchInfo/' + id);
    if (id != null) {
      this.matchService.getMatchesById(id).subscribe({
        next : (match) => {
          this.match = match;
          //console.log("This is the match object: " + JSON.stringify(match));
          },
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

