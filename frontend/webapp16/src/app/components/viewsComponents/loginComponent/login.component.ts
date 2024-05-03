import {Component, ViewEncapsulation} from '@angular/core';
import { AuthService } from '../../../services/auth.service'
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['login.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class LoginComponent {

  passwordError = '';

  constructor(private router: Router, private authService: AuthService) { }

  login(username: string, password: string) {
    this.authService.login(username, password).subscribe();
  }
  logout() {
    this.authService.logout();
  }
  register(name: string, email: string, password: string, passwordConfirm: string, date: string) {
    if (password !== passwordConfirm) {
      this.passwordError = 'Passwords do not match.';
      return;
    }
    const dateObject = new Date(date);
    this.authService.register(name, email, password, dateObject).subscribe();
  }

}
