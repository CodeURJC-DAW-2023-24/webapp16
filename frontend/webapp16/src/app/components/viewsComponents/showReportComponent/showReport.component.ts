import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-show-report',
  templateUrl: './showReport.component.html',
  styleUrls: ['./showReport.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ShowReportComponent implements OnInit {
  error: string | null = null;
  report: any;

  constructor(private titleService: Title) {}

  ngOnInit() {
    this.titleService.setTitle('Report page');
  }

}
