import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { catchError, of } from "rxjs";
import { ActionToast } from "../../../../toast";
import { CommonModule } from "@angular/common";
import { Products } from "../../../../http requests/products";

@Component({
    selector: "admin-products-update-component",
    templateUrl: "admin-products-update.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule, CommonModule]
})
export class AdminProductsUpdateComponent{

    product: Products = new Products;
    productId: any;
    showProductForm: Boolean = false;
    message = "";

    constructor(private client: AppService){}

    getProduct(form: any): void {
        this.product = new Products;
        if (this.productId) {
            this.client.productsGetById(this.productId).pipe(
                catchError((error) => {
                    console.error('Server error:', error);
                    this.message = "We encountered a problem retrieving the user data. Please try a different ID.";
                    let action = "<span class='text-danger'>Error</span>";
                    ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.errorColor);
                    // Optionally, return an observable with a default value or an error signal
                    return of(null); // This ensures the observable chain is not broken
                })
            ).subscribe(data => {
                if (data) {
                    this.product = data;
                    this.showProductForm = true;
                }
            });
        } else {
            this.message = "Enter User id to search records!";
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.errorColor);
        }
    }

    hideProductForm(){
        this.showProductForm = false;
    }

    updateProduct(form: any){
        if (form.valid) {
            
            this.client.productsUpdate(this.productId, this.product).subscribe(data=>{
                if (data) {
                    this.message = "Product "+data.name+" has been updated successfully.";
                    let action = "<span class='text-success'>Updated</span>";
                    ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.successColor);
                }
            });
        }else{
            this.message = "Please input all the values";
            let action = "<span class='text-danger'>Error</span>";
            ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.errorColor);
            
        }
    }
}