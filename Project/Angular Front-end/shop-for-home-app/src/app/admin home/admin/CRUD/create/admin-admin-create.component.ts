import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { User } from "../../../../http requests/user";
import { ActionToast } from "../../../../toast";


@Component({
    selector: "admin-admin-create-component",
    templateUrl: "admin-admin-create.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule]
})
export class AdminAdminCreateComponent{

    userNameRequested: Boolean = false;
    user: User = new User;

    constructor(private client: AppService){}

    createUser(form: any){
        
        if (form.valid) {
            this.client.adminCreate(this.user).subscribe(data=>{
                let action = "<span class='text-success'>Created</span>";
                let msg = "Admin "+data.name+" has been created successfully.";
                ActionToast.showToast(ActionToast.apps.adminApp, action, msg, ActionToast.action.successColor);
            });
        }else{
            let action = "<span class='text-danger'>Error</span>";
            let msg = "Please input all the values.";
            ActionToast.showToast(ActionToast.apps.adminApp, action, msg, ActionToast.action.errorColor);
        }
    }
}