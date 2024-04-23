// auth.guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionService } from './session.service';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private sessionService: SessionService, private router: Router) { }

  canActivate(
  next: ActivatedRouteSnapshot,
  state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  const expectedRole = next.data['expectedRole'];
  const userRole = this.sessionService.getRole();

    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return false;
    }

  if (!userRole) {
    return this.router.parseUrl('/login');
  }

  if (expectedRole && userRole !== expectedRole) {
    return this.router.parseUrl('/login');
  }

  return true;
  }
}
