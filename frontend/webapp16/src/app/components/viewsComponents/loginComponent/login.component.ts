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

  // login.component.ts
  login(username: string, password: string) {
    this.authService.login(username, password).subscribe(
      success => {
        if (!success) {
          // Handle incorrect password
          this.passwordError = 'Incorrect password';
        }
      },
      error => {
        // Handle other errors
      }
    );
  }
  logout() {
    this.authService.logout();
  }
  register(name: string, email: string, password: string, passwordConfirm: string, dateOfBirth: string) {
    if (password !== passwordConfirm) {
      this.passwordError = 'Passwords do not match.';
      return;
    }

    this.authService.register(name, email, password, dateOfBirth).subscribe(() => {
      this.login(name, password);
    });
  }

}
