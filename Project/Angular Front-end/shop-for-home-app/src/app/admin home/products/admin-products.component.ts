import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";

@Component({
    selector: "admin-products-component",
    templateUrl: "admin-products.component.html",
    styleUrls: ["admin-products.component.css"],
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class AdminProductsComponent{

}