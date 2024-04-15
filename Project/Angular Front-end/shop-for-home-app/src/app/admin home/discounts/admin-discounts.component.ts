import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";

@Component({
    selector: "admin-discounts-component",
    templateUrl: "admin-discounts.component.html",
    styleUrls: ["admin-discounts.component.css"],
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class AdminDiscountsComponent{

}