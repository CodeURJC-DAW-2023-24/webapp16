import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'aboutUs',
  templateUrl: './aboutUs.component.html',
  styleUrls: ['./aboutUs.component.css']
})
export class AboutUsComponent implements OnInit {
  constructor(private titleService: Title) { }

  ngOnInit(): void {
    this.titleService.setTitle('About Us');
  }
}
