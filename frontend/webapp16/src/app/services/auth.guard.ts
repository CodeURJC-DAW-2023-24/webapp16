// auth.guard.ts

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private sessionService: SessionService, private router: Router) { }

  canActivate(
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  const expectedRole = route.data['expectedRole'];
  const userRole = this.sessionService.getRole();

  if (!userRole) {
    return this.router.parseUrl('/login');
  }

  if (expectedRole && userRole !== expectedRole) {
    return this.router.parseUrl('/login');
  }

  return true;
}

}
