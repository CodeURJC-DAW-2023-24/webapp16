// session.service.ts

import { Injectable } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private user: User | null = null;

  setSession(user: User): void {
    this.user = user;
    localStorage.setItem('user', JSON.stringify(user));
  }

  clearSession(): void {
    this.user = null;
    localStorage.removeItem('user');
  }

  getUser(): User | null {
    return this.user;
  }

  getRole(): string | null {
    return this.user?.roles && this.user.roles.length > 0 ? this.user.roles[0] : null;
  }

  isAuthenticated(): boolean {
    return this.user !== null;
  }

  isAdmin(): boolean {
    return this.user?.roles?.includes('ADMIN') || false;
  }

  isUser(): boolean {
    return this.user?.roles?.includes('USER') || false;
  }


  canAccessAdmin(): boolean {
    return this.isAdmin();
  }

  canAccessUser(): boolean {
    return this.isUser() || this.isAdmin();
  }

  canAccessUnregistered(): boolean {
    return true;
  }
}
