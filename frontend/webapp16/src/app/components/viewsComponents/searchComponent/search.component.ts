import {Component, OnInit} from '@angular/core';
import { SearchService } from '../../../services/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{


  data: any;
  type = ''; //type of search

  constructor(private searchService: SearchService) { }

ngOnInit(): void {
  this.searchService.getData().subscribe(data => this.data = data);

  this.searchService.getType().subscribe(type => {
    this.type = type;
  });
}



}

