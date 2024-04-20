import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-fill-match-report',
  templateUrl: './fillMatchReport.component.html',
  styleUrls: ['./fillMatchReport.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FillMatchReportComponent implements OnInit {
  reportForm!: FormGroup;
  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.reportForm = this.fb.group({
      dateOfBirth: ['', Validators.required],
      matchTime: ['', Validators.required],
      // otros campos...
    });
  }

  onSubmit() {
    if (this.reportForm.valid) {
      console.log(this.reportForm.value);
      // lógica de envío aquí
    }
  }
}
