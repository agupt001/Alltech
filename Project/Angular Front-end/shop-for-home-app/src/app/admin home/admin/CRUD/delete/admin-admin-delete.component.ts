import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { catchError, of } from "rxjs";
import { FormsModule } from "@angular/forms";
import { ActionToast } from "../../../../toast";

@Component({
    selector: "admin-admin-delete-component",
    templateUrl: "admin-admin-delete.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule]
})
export class AdminAdminDeleteComponent{

    userId!: number;
    message = "";
    constructor(private client: AppService){ }

    deleteUser(){
        if (this.userId) {
            this.client.adminDelete(this.userId).pipe(
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
                    console.log(data);
                    this.message = "User has been deleted successfully.";
                    let action = "<span class='text-success'>Deleted</span>";
                    ActionToast.showToast(ActionToast.apps.adminApp, action, this.message, ActionToast.action.successColor);
                }
                
                
            });
        } else {
            this.message = "Enter User id to delete record!";
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(ActionToast.apps.adminApp, action, this.message, ActionToast.action.errorColor);
        }
        console.log(this.message);
        
    }
}