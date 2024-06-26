import {Component, ViewEncapsulation} from '@angular/core';
import { AuthService } from '../../../services/auth.service'
import {Router} from "@angular/router";
import { FormBuilder, Validators } from '@angular/forms';
import {formatDate} from "@angular/common";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['login.component.css'],
  encapsulation: ViewEncapsulation.None
})

export class LoginComponent {
  loginForm = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });
  registerForm = this.fb.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', Validators.required],
    passwordConfirm: ['', Validators.required],
    dateOfBirth: ['', Validators.required],
  });

  passwordError = '';
  fieldsError = '';

  constructor(private fb:FormBuilder, router: Router, private authService: AuthService) { }



  login() {
    this.resetPasswordError();
    this.resetFieldError();
    const usernameControl = this.loginForm.get('username');
    const passwordControl = this.loginForm.get('password');

    if (usernameControl && passwordControl && usernameControl.value && passwordControl.value) {
      const username = usernameControl.value;
      const password = passwordControl.value;
      this.authService.usernameExists(username).subscribe(
        exists => {
          if (!exists) {
            this.passwordError = 'User does not exist';
            usernameControl.setValue(''); // Clear the username field
            passwordControl.setValue(''); // Clear the password field
          } else {
            console.log('Logging in...');
            this.authService.login(username, password).subscribe(
              success => {
                if (!success) {
                  // Handle incorrect password
                  this.passwordError = 'Incorrect password';
                  passwordControl.setValue(''); // Clear the password field
                }
              },
              error => {
                if (error === 'User not found') {
                  // Handle user not found
                  this.passwordError = 'User does not exist';
                } else {
                  // Handle other errors
                  this.passwordError = 'An error occurred';
                }
              }
            );
          }
        },
        error => {
          // Handle error
          this.passwordError = 'An error occurred';
        }
      );
    } else {
      // Handle form validation errors
      this.passwordError = 'All fields are required.';
    }
  }

  logout() {
    this.authService.logout();
  }
  register() {
    this.resetPasswordError();
    this.resetFieldError();
    const nameControl = this.registerForm.get('name');
    const emailControl = this.registerForm.get('email');
    const passwordControl = this.registerForm.get('password');
    const passwordConfirmControl = this.registerForm.get('passwordConfirm');
    const dateOfBirthControl = this.registerForm.get('dateOfBirth');

    if (nameControl && emailControl && passwordControl && passwordConfirmControl && dateOfBirthControl &&
      nameControl.value && emailControl.value && passwordControl.value && passwordConfirmControl.value && dateOfBirthControl.value) {
      const name = nameControl.value;
      const email = emailControl.value;
      const password = passwordControl.value;
      const passwordConfirm = passwordConfirmControl.value;
      const dateOfBirth = dateOfBirthControl.value;

      const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
      if (!emailPattern.test(email)) {
        this.passwordError = 'The email format is not valid';
        emailControl.setValue('');
        return;
      }

      if (password !== passwordConfirm) {
        this.passwordError = 'The passwords do not match';
        passwordControl.setValue(''); // Clear the password field
        passwordConfirmControl.setValue(''); // Clear the password confirmation field
        return;
      }
      const dateOfBirthFormat = formatDate(dateOfBirthControl.value, 'yyyy-MM-dd', 'en-US');
      console.log('Date of birth before register:', dateOfBirthFormat);

      this.authService.register(name, email, password, dateOfBirthFormat).subscribe(
        () => {
          // After successful registration, login with the new credentials
          this.authService.login(name, password).subscribe(
            success => {
              if (!success) {
                // Handle incorrect password
                this.passwordError = 'Wrong password';
                passwordControl.setValue(''); // Clear the password field
              }
            },
            error => {
              // Handle other errors
              this.passwordError = 'Error during login';
              passwordControl.setValue(''); // Clear the password field
            }
          );
        },
        error => {
          console.log(error); // Print the full error to the console
          if (error.status === 400) {
            // Handle username already exists error
            this.passwordError = 'Username already exists';
            nameControl.setValue(''); // Clear the name field
          } else {
            // Handle other errors
            this.passwordError = 'Error during registration';
            passwordControl.setValue(''); // Clear the password field
          }
        }
      );
    } else {
      this.fieldsError = 'All fields are required.';
    }
  }
  resetPasswordError() {
    this.passwordError = '';
  }
  resetFieldError() {
    this.fieldsError = '';
  }
}
