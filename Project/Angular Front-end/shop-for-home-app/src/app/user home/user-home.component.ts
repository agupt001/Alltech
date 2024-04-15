import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../app.service";
import { Cart } from "../http requests/cart";
import { CartCountService } from "./cart/cart-count.service";
import { CartCount } from "../http requests/cart-count";
import { HandleToken } from "../http requests/handle-token";

@Component({
    selector: "user-home-component",
    templateUrl: "user-home.component.html",
    standalone: true,
    styleUrls: ["user-home.component.css"],
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class UserHomeComponent implements OnInit{
    
    cartCount: number = 0;
    userId = HandleToken.userId;
    
    constructor(private client: AppService, private cartCountService: CartCountService){ };

    ngOnInit(): void {
        this.client.getUserCart(this.userId).subscribe(data=>{
            this.cartCountService.updateCartCount(data.length);
            this.cartCountService.currentCartCount.subscribe(count=>{
                this.cartCount = count;
            });
            CartCount.cart_item_number = this.cartCount;
        });
    }   
}