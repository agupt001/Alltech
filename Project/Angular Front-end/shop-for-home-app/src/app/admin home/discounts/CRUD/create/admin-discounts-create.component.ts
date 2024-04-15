import { Component } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { AppService } from "../../../../app.service";
import { FormsModule } from "@angular/forms";
import { ActionToast } from "../../../../toast";
import { Products } from "../../../../http requests/products";
import { DiscountDml } from "../../../../http requests/discount-dml";
import { UsersResponse } from "../../../../http response/users-response";
import { catchError, of } from "rxjs";
import { CommonModule } from "@angular/common";


@Component({
    selector: "admin-discounts-create-component",
    templateUrl: "admin-discounts-create.component.html",
    styleUrl: "admin-discounts-create.component.css",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive, FormsModule, CommonModule]
})
export class AdminDiscountsCreateComponent{

    discount: DiscountDml = new DiscountDml;

    user: UsersResponse = new UsersResponse;
    message = "";

    product: Products = new Products;

    disableBtn = true;
    userDetailsBool = false;
    prodDetailsBool = false;

    animateId = "";
    heightClass = "card detailsCard";

    animateIdProd = "";
    heightClassProd = "card detailsCard";

    createBtn = "Create";
    deleteBtnActivated = false;

    constructor(private client: AppService){}

    createDiscount(form: any){
        
        if (form.valid) {
            if (this.createBtn === "Create") {
                this.client.discountsCreate(this.discount).subscribe(data=>{
                    let action = "<span class='text-success'>Created</span>";
                    let msg = "Discount has been created successfully.";
                    ActionToast.showToast(ActionToast.apps.discountsApp, action, msg, ActionToast.action.successColor);
                    this.resetModule();
                });
            } else {
                this.client.discountsUpdate(this.discount).subscribe(data=>{
                    let action = "<span class='text-success'>Updated</span>";
                    let msg = "Discount has been updated successfully.";
                    ActionToast.showToast(ActionToast.apps.discountsApp, action, msg, ActionToast.action.successColor);
                    this.resetModule();
                });
            }
            
        }else{
            let action = "<span class='text-danger'>Error</span>";
            let msg = "Please input all the values.";
            ActionToast.showToast(ActionToast.apps.discountsApp, action, msg, ActionToast.action.errorColor);
        }
    }

    deleteDiscount(){
        this.client.discountsDelete(this.discount).subscribe(data=>{
            let action = "<span class='text-success'>Deleted</span>";
            let msg = "Discount has been deleted successfully.";
            ActionToast.showToast(ActionToast.apps.discountsApp, action, msg, ActionToast.action.successColor);
            this.resetModule();
        });
    }

    resetModule(){
        this.discount = new DiscountDml;
        this.user = new UsersResponse;
        this.product = new Products;
        this.disableBtn = true;
        this.userDetailsBool = false;
        this.prodDetailsBool = false;
        this.animateId = "";
        this.heightClass = "card detailsCard";
        this.animateIdProd = "";
        this.heightClassProd = "card detailsCard";
        this.createBtn = "Create";
        this.deleteBtnActivated = false;
        this.message = "";
    }

    getUserDetails(): void {
        if(this.userDetailsBool){
            this.animateId = "";
        }else{
            this.animateId = "animate";
        }
        this.user = new UsersResponse;
        
        if (this.discount.userId) {
            this.client.userGetById(this.discount.userId).pipe(
                catchError((error) => {
                    console.error('Server error:', error);
                    this.message = "We encountered a problem retrieving the user data. Please try a different ID.";
                    let action = "<span class='text-danger'>Error</span>";
                    this.userDetailsBool = false;
                    this.heightClass = "card detailsCard";
                    ActionToast.showToast(ActionToast.apps.userApp, action, this.message, ActionToast.action.errorColor);
                    // Optionally, return an observable with a default value or an error signal
                    return of(null); // This ensures the observable chain is not broken
                })
            ).subscribe(data => {
                if (data) {
                    this.user = data;
                    this.userDetailsBool = true;
                    setTimeout(() => {
                        this.heightClass = "card detailsCard addHeight";
                    }, 1000);
                    
                    this.enableCreateBtn();
                }
            });
        } else {
            this.message = "Enter User id to create discount!";
            let action = "<span class='text-danger'>Error</span>";
            this.heightClass = "card detailsCard";
            this.userDetailsBool = false;
            ActionToast.showToast(ActionToast.apps.userApp, action, this.message, ActionToast.action.errorColor);
        }
        this.enableCreateBtn();
    }

    getProductDetails(): void {
        if(this.prodDetailsBool){
            this.animateIdProd = "";
        }else{
            this.animateIdProd = "animateProd";
        }
        this.product = new Products;
        
        if (this.discount.product_id) {
            this.client.productsGetById(this.discount.product_id).pipe(
                catchError((error) => {
                    console.error('Server error:', error);
                    this.message = "We encountered a problem retrieving the product data. Please try a different ID.";
                    let action = "<span class='text-danger'>Error</span>";
                    this.prodDetailsBool = false;
                    this.heightClassProd = "card detailsCard";
                    ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.errorColor);
                    return of(null); // This ensures the observable chain is not broken
                })
            ).subscribe(data => {
                if (data) {
                    this.product = data;
                    this.prodDetailsBool = true;
                    setTimeout(() => {
                        this.heightClassProd = "card detailsCard addHeightProd";
                    }, 1000);
                    this.enableCreateBtn();
                }
            });
        } else {
            this.message = "Enter product id to search records!";
            let action = "<span class='text-danger'>Error</span>";
            this.prodDetailsBool = false;
            this.heightClassProd = "card detailsCard";
            ActionToast.showToast(ActionToast.apps.productsApp, action, this.message, ActionToast.action.errorColor);
        }
        this.enableCreateBtn();
    }

    enableCreateBtn(){
        this.disableBtn = true;
        if (this.userDetailsBool && this.prodDetailsBool) {
            // Ask to update the record if found!
            this.discountUpdateModule();
            this.disableBtn = false;
        }
    }

    discountUpdateModule(){
        this.client.discountsReadAll().subscribe(data =>{
            let discountFound = data.some(disc => (disc.userId === this.user.id && disc.product_id === this.product.id));
            if (discountFound) {
                let discountObj = data.filter(disc => (disc.userId === this.user.id && disc.product_id === this.product.id));
                discountObj.forEach(element => {
                    this.discount = element;
                });
                this.createBtn = "Update";
                this.deleteBtnActivated = true;
            }else{
                this.discount.discount_price =  "";
                this.createBtn = "Create";
                this.deleteBtnActivated = false;
            }
        });
    }

}