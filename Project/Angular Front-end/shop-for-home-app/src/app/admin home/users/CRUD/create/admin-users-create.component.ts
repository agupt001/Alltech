import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { User } from "../../../../http requests/user";
import { ActionToast } from "../../../../toast";


@Component({
    selector: "admin-users-create-component",
    templateUrl: "admin-users-create.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule]
})
export class AdminUsersCreateComponent{

    userNameRequested: Boolean = false;
    user: User = new User;

    constructor(private client: AppService){}

    createUser(form: any){
        
        if (form.valid) {
            this.client.userCreate(this.user).subscribe(data=>{
                let action = "<span class='text-success'>Created</span>";
                let msg = "User "+data.name+" has been created successfully.";
                ActionToast.showToast(ActionToast.apps.userApp, action, msg, ActionToast.action.successColor);
            });
        }else{
            let action = "<span class='text-danger'>Error</span>";
            let msg = "Please input all the values.";
            ActionToast.showToast(ActionToast.apps.userApp, action, msg, ActionToast.action.errorColor);
        }
    }
}