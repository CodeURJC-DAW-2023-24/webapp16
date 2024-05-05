// En showReport.component.ts
import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ActivatedRoute} from '@angular/router';
import {MatchService} from '../../../services/match.service';
import {ReportsService} from "../../../services/reports.service";
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-show-report',
  templateUrl: './showReport.component.html',
  styleUrls: ['./showReport.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ShowReportComponent implements OnInit {
  error: string | null = null;
  report: any;

  constructor(private titleService: Title, private reportService: ReportsService, private route: ActivatedRoute) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.titleService.setTitle('Report page');
      this.reportService.getReportById(id).subscribe(
        (report: any) => {
          this.report = report;
          console.log(this.report);
        }
      );
    }
  }

 genPDF() {
  let data = document.getElementById('allInfo');  // id of the form
  if (data) {
    html2canvas(data).then(canvas => {
      // Few necessary setting options
      let imgWidth = 208;
      let pageHeight = 295;
      let imgHeight = canvas.height * imgWidth / canvas.width;
      let heightLeft = imgHeight;

      const contentDataURL = canvas.toDataURL('image/png')
      let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF
      let position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
      let name = 'ReportMatch/' + this.report.match.id;
      pdf.save(name); // Generated PDF
    });
  } else {
    console.error('Element with id "allInfo" not found');
  }
}
}
