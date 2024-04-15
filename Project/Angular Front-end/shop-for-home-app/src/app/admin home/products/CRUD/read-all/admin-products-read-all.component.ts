import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { Products } from "../../../../http requests/products";

@Component({
    selector: "admin-products-read-all-component",
    templateUrl: "admin-products-read-all.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class AdminProductsReadAllComponent implements OnInit{

    products: Products[] = [];

    constructor(private client: AppService){ }
    

    ngOnInit(): void {
        this.client.getAllProducts().subscribe(data=>{
            this.products = data;
        });
    }

}