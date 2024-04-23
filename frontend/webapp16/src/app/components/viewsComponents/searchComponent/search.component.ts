import { Component } from '@angular/core';
import { SearchService } from '../../../services/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  results: any;

  constructor(private searchService: SearchService) { }
  ngOnInit(): void {
    this.searchService.search('Lionel').subscribe(results => {
      this.results = results;
    });
  }
}

