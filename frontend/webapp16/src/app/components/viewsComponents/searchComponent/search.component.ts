import { Component } from '@angular/core';
import { SearchService } from '../../../services/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {


  data: any;
  type = ''; //type of search

  constructor(private searchService: SearchService) { }


ngOnInit(): void {
  this.data = this.searchService.getData();
  this.type = this.searchService.getType();
}




}

