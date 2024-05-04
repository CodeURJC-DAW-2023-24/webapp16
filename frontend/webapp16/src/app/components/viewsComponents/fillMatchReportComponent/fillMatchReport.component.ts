import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {MatchService} from "../../../services/match.service";
import {ActivatedRoute, Router} from "@angular/router";
import {throwError} from "rxjs";

@Component({
  selector: 'app-fill-match-report',
  templateUrl: './fillMatchReport.component.html',
  styleUrls: ['./fillMatchReport.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FillMatchReportComponent implements OnInit {
  reportForm!: FormGroup;
  report: any;
  matchId: any;
  match: any;
  constructor(private fb: FormBuilder, private matchService: MatchService, private route: ActivatedRoute, private router: Router) {
    this.reportForm = this.fb.group({
      date: ['', Validators.required],
      time: ['', Validators.required],
      localTeamGoals: ['', [Validators.required, Validators.min(0)]],
      visitingTeamGoals: ['', [Validators.required, Validators.min(0)]],
      matchOfficials: ['', Validators.required],
      observations: ['', Validators.required],
      localTeam: ['', Validators.required],
      visitingTeam: ['', Validators.required]
    });
  }

  ngOnInit() {
    const Id = this.route.snapshot.paramMap.get('id');
    if (Id !== null) {
      this.matchService.getMatchesById(Id).subscribe(
        match => {
          this.matchId = match.id;
          this.match = match;
          this.reportForm.patchValue({
            localTeam: {value: this.match.localTeam.name, disabled: true},
            visitingTeam: {value: this.match.visitingTeam.name, disabled: true},
          });
        },
        error => {
          const errorCode = error.status;
          this.router.navigate(['/error'], { state: { errorCode: errorCode } });
          return throwError(error);
        }
      );
    } else {
      throw new Error("Match ID is null");
    }
  }

  onSubmit() {
    if (this.reportForm.valid) {
      const report = {
        date: this.reportForm.value.date,
        time: this.reportForm.value.time,
        matchOfficials: this.reportForm.value.matchOfficials,
        localTeamGoals: this.reportForm.value.localTeamGoals,
        visitingTeamGoals: this.reportForm.value.visitingTeamGoals,
        observations: this.reportForm.value.observations,
        match: this.match,
      };
      console.log(report);
      this.matchService.postMatchReport(report).subscribe(
        response => {
          console.log(response);
          // lógica de éxito aquí
        },
        error => {
          console.log(error);
          // lógica de error aquí
        }
      );
    }
    else{
      console.log("Invalid form");
    }
  }
}
