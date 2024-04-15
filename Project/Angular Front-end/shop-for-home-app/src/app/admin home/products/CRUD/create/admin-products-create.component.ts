import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { ActionToast } from "../../../../toast";
import { Products } from "../../../../http requests/products";


@Component({
    selector: "admin-products-create-component",
    templateUrl: "admin-products-create.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule]
})
export class AdminProductsCreateComponent{

    product: Products = new Products;

    constructor(private client: AppService){}

    createUser(form: any){
        
        if (form.valid) {
            this.client.productsCreate(this.product).subscribe(data=>{
                let action = "<span class='text-success'>Created</span>";
                let msg = "Product "+data.name+" has been created successfully.";
                ActionToast.showToast(ActionToast.apps.productsApp, action, msg, ActionToast.action.successColor);
            });
        }else{
            let action = "<span class='text-danger'>Error</span>";
            let msg = "Please input all the values.";
            ActionToast.showToast(ActionToast.apps.productsApp, action, msg, ActionToast.action.errorColor);
        }
    }
}