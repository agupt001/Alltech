import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../app.service";
import { SalesResponse } from "../../http response/sales-response";
import { CurrencyPipe, DatePipe } from "@angular/common";

@Component({
    selector: "admin-sales-component",
    templateUrl: "admin-sales.component.html",
    styleUrls: ["admin-sales.component.css"],
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, DatePipe, CurrencyPipe],
})
export class AdminSalesComponent implements OnInit{

    sales: SalesResponse[] = [];
    totalSale: number = 0;
    constructor(private client: AppService){ }
    
    ngOnInit(): void {
        this.client.getAdminAllSales().subscribe(data=>{
            this.sales = data;

            this.sales.forEach(sale => {
                this.totalSale += sale.final_price;
            });
        });

        
    }

}