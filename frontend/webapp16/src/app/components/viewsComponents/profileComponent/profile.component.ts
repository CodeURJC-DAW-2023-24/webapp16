import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user.model';
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userProfile: User;

  constructor(private userService: UserService, private titleService: Title) {
    this.userProfile = new User(0, '');
  }

  ngOnInit(): void {
    this.titleService.setTitle('Profile');
  this.userService.getUserProfile().subscribe({
    next: (data: User) => {
      this.userProfile = data;

    },
    error: (error) => {
     // console.error('Error occurred while fetching user profile:', error);
    }
  });
}

onSubmit(): void {
  this.userService.updateUserProfile(this.userProfile).subscribe({
    next: (data: User) => {
      this.userProfile = data;
    },
    error: (error) => {
      //console.error('Error occurred while updating user profile:', error);
    }
  });
}
}
