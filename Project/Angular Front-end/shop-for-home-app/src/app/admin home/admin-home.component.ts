import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";

@Component({
    selector: "admin-home-component",
    templateUrl: "admin-home.component.html",
    standalone: true,
    styleUrls: ["admin-home.component.css"],
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class AdminHomeComponent {

}