import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { Products } from "../../http requests/products";
import { AppService } from "../../app.service";

@Component({
    selector: "admin-stocks-component",
    templateUrl: "admin-stocks.component.html",
    styleUrl: "admin-stocks.component.css",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class AdminStocksComponent implements OnInit{

    products: Products[] = [];

    constructor(private client: AppService){ }
    

    ngOnInit(): void {
        this.client.getAllProducts().subscribe(data=>{
            this.products = data;
        });
    }
}