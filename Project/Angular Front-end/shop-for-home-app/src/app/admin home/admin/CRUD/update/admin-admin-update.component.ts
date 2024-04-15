import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { User } from "../../../../http requests/user";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { catchError, of } from "rxjs";
import { ActionToast } from "../../../../toast";
import { CommonModule } from "@angular/common";

@Component({
    selector: "admin-admin-update-component",
    templateUrl: "admin-admin-update.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule, CommonModule]
})
export class AdminAdminUpdateComponent{

    userNameRequested: Boolean = false;
    user: User = new User;
    userId: any;
    showUserForm: Boolean = false;
    message = "";

    phoneNumberError: boolean = false;

    constructor(private client: AppService){}

    getUser(form: any): void {
        this.user = new User;
        if (this.userId) {
            this.client.adminGetById(this.userId).pipe(
                catchError((error) => {
                    console.error('Server error:', error);
                    this.message = "We encountered a problem retrieving the user data. Please try a different ID.";
                    let action = "<span class='text-danger'>Error</span>";
                    ActionToast.showToast(ActionToast.apps.adminApp, action, this.message, ActionToast.action.errorColor);
                    // Optionally, return an observable with a default value or an error signal
                    return of(null); // This ensures the observable chain is not broken
                })
            ).subscribe(data => {
                if (data) {
                    this.user = data;
                    this.showUserForm = true;
                }
            });
        } else {
            this.message = "Enter User id to search records!";
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(ActionToast.apps.adminApp, action, this.message, ActionToast.action.errorColor);
        }
    }

    hideUserForm(){
        this.showUserForm = false;
    }

    updateUser(form: any){
        if (form.valid) {
            
            this.client.adminUpdate(this.userId, this.user).subscribe(data=>{
                if (data) {
                    this.message = "User "+data.name+" has been updated successfully.";
                    let action = "<span class='text-success'>Updated</span>";
                    ActionToast.showToast(ActionToast.apps.adminApp, action, this.message, ActionToast.action.successColor);
                }
            });
        }else{
            this.message = "Please input all the values";
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(ActionToast.apps.adminApp, action, this.message, ActionToast.action.errorColor);
            
        }
    }

    

  validatePhoneNumber() {
    // Basic validation to check if the input is numeric and up to 10 digits
    const isValid = /^\d{0,10}$/.test(this.user.phone);
    this.phoneNumberError = !isValid;
  }
}