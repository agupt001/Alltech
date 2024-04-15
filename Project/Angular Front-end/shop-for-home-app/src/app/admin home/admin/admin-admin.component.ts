import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";

@Component({
    selector: "admin-admin-component",
    templateUrl: "admin-admin.component.html",
    styleUrls: ["admin-admin.component.css"],
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class AdminAdminComponent{

}