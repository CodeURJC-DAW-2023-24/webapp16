import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.model';
import {Title} from "@angular/platform-browser";
import { MatSnackBar } from '@angular/material/snack-bar';
import {animate, state, style, transition, trigger} from "@angular/animations";
import { AuthService } from '../../../services/auth.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  animations: [
    trigger('state', [
      state('done', style({
        // Define the styles for the 'done' state
      })),
      transition('* => done', animate('1s'))
    ])
  ]
})
export class ProfileComponent implements OnInit {
  userProfile: User;
  updatedUser: User;
  oldUsername: string; // Add this line

  constructor(private authService: AuthService, private userService: UserService, private titleService: Title, private snackBar: MatSnackBar, private router: Router) {
    this.userProfile = new User(0, '');
    this.updatedUser = new User(0, '');
    this.oldUsername = '';
  }

  ngOnInit(): void {
    this.titleService.setTitle('Profile');
    this.userService.getUserProfile().subscribe({
      next: (data: User) => {
        this.userProfile = data;
        // Copy the user profile to the updated user
        this.updatedUser = {...data};
        this.oldUsername = this.updatedUser.name; // Add this line
      },
      error: (error) => {
        console.error('Error occurred while fetching user profile:', error);
      }
    });
  }

  onSubmit(): void {
    if (this.authService.isLoggedIn) {
      if (this.authService.isUsernameChanged(this.oldUsername, this.updatedUser.name)) { // Change this line
        // Muestra un mensaje de confirmación al usuario
        if (confirm('Your username has changed. You will need to log in again. Do you want to continue?')) {
          this.updateUserProfile();
        }
      } // Check if any other field has changed
      else if (JSON.stringify(this.userProfile) !== JSON.stringify(this.updatedUser)) {
        // Show a confirmation message for other changes
        if (confirm('You have made changes to your profile. Do you want to continue?')) {
          this.updateUserProfile();
        }
      } else {
        this.updateUserProfile();
      }
    } else {
      console.error('User is not logged in. Redirecting to login page.');
      this.router.navigate(['/login']);
    }
  }

  updateUserProfile(): void {
    this.userService.updateUserProfile(this.updatedUser).subscribe({
      next: (data: User) => {
        this.userProfile = data;
        // Copy the updated user profile back to the updated user
        this.updatedUser = {...data};
        console.log('Old username:', this.oldUsername); // Change this line
        console.log('New username:', this.updatedUser.name);
        // If the username has changed, log out the user and redirect to the login page
        if (this.authService.isUsernameChanged(this.oldUsername, this.updatedUser.name)) { // Change this line
          console.log('Username has changed. Logging out the user...');
          this.authService.logout().subscribe(() => {
            console.log('Redirecting to login page...');
            this.router.navigate(['/login']);
          });
        }
      },
      error: (error) => {
        console.log('Error occurred while updating user profile:', error);
        console.error('Error occurred while updating user profile:', error);
      }
    });
  }
}
