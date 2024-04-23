import {Component, ViewEncapsulation} from '@angular/core';
import { AuthService } from '../../../services/auth.service'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['login.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class LoginComponent {
constructor(private authService: AuthService) { }

login(username: string, password: string) {
  this.authService.login(username, password).subscribe();
  }
  logout() {
    this.authService.logout();
  }

}
