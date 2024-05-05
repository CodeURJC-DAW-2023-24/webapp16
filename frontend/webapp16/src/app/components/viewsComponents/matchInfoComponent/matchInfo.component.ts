import {Component, OnInit} from '@angular/core';
import {MatchService} from "../../../services/match.service";
import {Observable, throwError} from "rxjs";
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";
import {ReportsService} from "../../../services/reports.service";

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
  hasReport: boolean = false;

  constructor(public authService: AuthService,private titleService: Title, private route: ActivatedRoute, private router: Router, private matchService: MatchService, private reportService: ReportsService) {
    this.isAdmin$ = this.authService.isAdmin();
  }
  ngOnInit(): void {

    this.isAdmin$ = this.authService.isAdmin();
    const id = this.route.snapshot.paramMap.get('id'); //get match ID from URL
    this.matchId= id;
    //this.hasReport = !!this.match.report;

    this.titleService.setTitle('matchInfo/' + id);
    if (id != null) {
      this.matchService.getMatchesById(id).subscribe({
        next : (match) => {
          this.match = match;
          console.log(this.matchId)
          this.reportService.getReportById(this.matchId).subscribe((report: any) => {
            console.log("This is the report object: " + !!report);
            this.hasReport = !!report; // Set hasReport to true if report exists
          });
          //console.log("This is the match object: " + JSON.stringify(match));
          },
        error: (error) => {
          const errorCode = error.status;
          this.router.navigate(['/login'], { state: { errorCode: errorCode } });
          return throwError(error);
        }
      }
      );


    }
  }
}

