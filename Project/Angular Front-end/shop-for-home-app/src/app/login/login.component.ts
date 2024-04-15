import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { User, UserCredentials } from '../http requests/user';
import { AppService } from '../app.service';
import { ActionToast } from '../toast';
import { catchError, of } from 'rxjs';
import { HandleToken } from '../http requests/handle-token';

@Component({
  selector: 'login-component',
  templateUrl: 'login.component.html',
  styleUrl: 'login.component.css',
  standalone: true,
  imports: [FormsModule, CommonModule],
})
export class LoginComponent {
  isFlipped = false;
  user: UserCredentials = new UserCredentials();

  constructor(private router: Router, private client: AppService) {}

  userLoginCredentials = {
    username: '',
    password: '',
  };

  isAdmin: Boolean = false;

  submit(form: any) {

    // Logic to validate login
    if (this.isAdmin) {
      this.client.adminReadAll().subscribe(data=>{
        let loggedInAdmin = 
        data.filter(admin=> 
          (admin.username === this.userLoginCredentials.username && 
            admin.password === this.userLoginCredentials.password));
        if (loggedInAdmin.length>0) {
          let message = 'Login successful';
              let action = "<span class='text-success'>Success</span>";
              ActionToast.showToast(
                ActionToast.apps.userApp,
                action,
                message,
                ActionToast.action.successColor
              );
              
          HandleToken.userId = loggedInAdmin[0].id;
          this.router.navigate(['/admin']);
        } else {
          let message = 'Username or password is incorrect.';
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(
              ActionToast.apps.userApp,
              action,
              message,
              ActionToast.action.errorColor
            );
        }
      });
      
    } else {
      this.client
        .getToken(this.userLoginCredentials)
        .pipe(
          catchError((error) => {
            console.error('Server error:', error);
            let message = 'Username or password is incorrect.';
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(
              ActionToast.apps.userApp,
              action,
              message,
              ActionToast.action.errorColor
            );
            // Optionally, return an observable with a default value or an error signal
            return of(null); // This ensures the observable chain is not broken
          })
        )
        .subscribe((data) => {
          if (data) {
            this.client.userReadAll().subscribe(data2=>{
              let loggedInUser = data2.filter(userDb => userDb.username === this.userLoginCredentials.username)[0].id;
              HandleToken.userId = loggedInUser;
              let message = 'Login successful';
              let action = "<span class='text-success'>Success</span>";
              ActionToast.showToast(
                ActionToast.apps.userApp,
                action,
                message,
                ActionToast.action.successColor
              );
              HandleToken.token = data;
              this.router.navigate(['/user']);
            })
          }           
        });
    }
  }

  flipCard() {
    this.isFlipped = !this.isFlipped;
  }

  createUser(form: any) {
    // Create user
    if (form.valid) {
      this.client
        .registerUser(this.user)
        .pipe(
          catchError((error) => {
            console.error('Server error:', error);
            let message = 'Something went wrong.';
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(
              ActionToast.apps.userApp,
              action,
              message,
              ActionToast.action.errorColor
            );
            // Optionally, return an observable with a default value or an error signal
            return of(null); // This ensures the observable chain is not broken
          })
        )
        .subscribe((data) => {
          if (data) {
            console.log(data);
            let action = "<span class='text-success'>Created</span>";
            let msg = 'User has been created successfully.';
            ActionToast.showToast(
              ActionToast.apps.userApp,
              action,
              msg,
              ActionToast.action.errorColor
            );
            this.flipCard();
          }
        });
    }
  }
}
