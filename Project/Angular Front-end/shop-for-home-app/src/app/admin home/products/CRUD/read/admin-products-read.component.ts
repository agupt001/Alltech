import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { UsersResponse } from "../../../../http response/users-response";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { catchError, of } from "rxjs";
import { ActionToast } from "../../../../toast";
import { Products } from "../../../../http requests/products";

@Component({
    selector: "admin-products-read-component",
    templateUrl: "admin-products-read.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule]
})
export class AdminProductsReadComponent{

    product: Products = new Products;
    productId!: number;
    message = "Enter Product id to search records!";

    constructor(private client: AppService){ }

    getProduct(): void {
        this.product = new Products;
        if (this.productId) {
            this.client.productsGetById(this.productId).pipe(
                catchError((error) => {
                    console.error('Server error:', error);
                    this.message = "We encountered a problem retrieving the product data. Please try a different ID.";
                    let action = "<span class='text-danger'>Error</span>";
                    ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.errorColor);
                    // Optionally, return an observable with a default value or an error signal
                    return of(null); // This ensures the observable chain is not broken
                })
            ).subscribe(data => {
                if (data) {
                    this.product = data;
                }
            });
        } else {
            this.message = "Enter product id to search records!";
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.errorColor);
        }
    }
}