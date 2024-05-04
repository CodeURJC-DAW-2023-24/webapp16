// En showReport.component.ts
import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ActivatedRoute} from '@angular/router';
import {MatchService} from '../../../services/match.service';

@Component({
  selector: 'app-show-report',
  templateUrl: './showReport.component.html',
  styleUrls: ['./showReport.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ShowReportComponent implements OnInit {
  error: string | null = null;
  report: any;

  constructor(private titleService: Title, private matchService: MatchService, private route: ActivatedRoute) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.titleService.setTitle('Report page');
      this.matchService.getReportById(id).subscribe(
        (report: Report) => {
          this.report = report;
          console.log(this.report);
        }
      );
    }
  }

}
