import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { UsersResponse } from "../../../../http response/users-response";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { catchError, of } from "rxjs";
import { ActionToast } from "../../../../toast";
import { PhoneNumberPipe } from "../../../../phoneNumberPipe";

@Component({
    selector: "admin-users-read-component",
    templateUrl: "admin-users-read.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule, PhoneNumberPipe]
})
export class AdminUsersReadComponent{

    user: UsersResponse = new UsersResponse;
    userId!: number;
    message = "Enter User id to search records!";

    constructor(private client: AppService){ }

    getUser(): void {
        this.user = new UsersResponse;
        if (this.userId) {
            this.client.userGetById(this.userId).pipe(
                catchError((error) => {
                    console.error('Server error:', error);
                    this.message = "We encountered a problem retrieving the user data. Please try a different ID.";
                    let action = "<span class='text-danger'>Error</span>";
                    ActionToast.showToast(ActionToast.apps.userApp, action, this.message, ActionToast.action.errorColor);
                    // Optionally, return an observable with a default value or an error signal
                    return of(null); // This ensures the observable chain is not broken
                })
            ).subscribe(data => {
                if (data) {
                    this.user = data;
                }
            });
        } else {
            this.message = "Enter User id to search records!";
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(ActionToast.apps.userApp, action, this.message, ActionToast.action.errorColor);
        }
    }
}