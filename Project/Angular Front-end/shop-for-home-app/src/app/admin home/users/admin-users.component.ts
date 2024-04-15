import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";

@Component({
    selector: "admin-users-component",
    templateUrl: "admin-users.component.html",
    styleUrls: ["admin-users.component.css"],
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class AdminUsersComponent{

}