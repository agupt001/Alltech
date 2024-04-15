import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { Products } from "../../../../http requests/products";
import { Discounts } from "../../../../http requests/discounts";
import { DiscountDml } from "../../../../http requests/discount-dml";
import { UsersResponse } from "../../../../http response/users-response";
import { PhoneNumberPipe } from "../../../../phoneNumberPipe";

@Component({
    selector: "admin-discounts-read-all-component",
    templateUrl: "admin-discounts-read-all.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, PhoneNumberPipe]
})
export class AdminDiscountsReadAllComponent implements OnInit{

    discounts: DiscountDml[] = [];
    discountDetails = [{
        "discount_id": "",
        "user": new UsersResponse,
        "product": new Products,
        "discount_price": ""
    }];

    constructor(private client: AppService){ }

    ngOnInit(): void {
        this.client.discountsReadAll().subscribe(data=>{
            this.discounts = data;
            data.forEach(discount => {
                this.client.userGetById(discount.userId).subscribe(userData => {
                    this.client.productsGetById(discount.product_id).subscribe(prodData => {
                        let tempObj = {
                            "discount_id": discount.id,
                            "user": userData,
                            "product": prodData,
                            "discount_price": discount.discount_price
                        };
                        this.discountDetails.push(tempObj);
                    });
                });
            });
        });
        this.discountDetails.splice(0,1);
        
    }
}