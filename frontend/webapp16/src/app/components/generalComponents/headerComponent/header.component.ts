import { Component } from '@angular/core';
import {query} from "@angular/animations";
import {Router} from "@angular/router";

@Component({
  selector: 'header-web',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})

export class HeaderComponent {

  constructor(private router: Router) { }

  search(query: string): void {
    this.router.navigate(['/search'], { queryParams: { query: query } });
  }
}
