import {Component, OnInit} from '@angular/core';
import { SearchService } from '../../../services/search.service';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{


  data: any;
  type = ''; //type of search

  constructor(private searchService: SearchService, private titleService: Title) { }

  ngOnInit(): void {
    this.titleService.setTitle('Search Page');
    this.searchService.getData().subscribe(data => this.data = data);

    this.searchService.getType().subscribe(type => {
      this.type = type;
    });
  }



}

