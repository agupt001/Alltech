import { Component, OnInit } from "@angular/core";
import { Discounts } from "../../http requests/discounts";
import { AppService } from "../../app.service";
import { ImageSearchService } from "../../image-search.service";
import { forkJoin } from "rxjs";
import { HandleToken } from "../../http requests/handle-token";

@Component({
    selector: "user-discounts",
    templateUrl: "discounts.component.html",
    standalone: true
})
export class UserDiscounts implements OnInit{
    spinner: Boolean = false;
    discounts: Discounts[]= [];
    total_value = 0;
    userId = HandleToken.userId; // Change this from login page

    constructor(private client: AppService, private imageSearchClient: ImageSearchService){ }
    
    ngOnInit(): void {
      this.spinner = true;
      forkJoin({
        discounts: this.client.getUserDiscounts(this.userId)
      }).subscribe(({ discounts }) => {
        this.discounts = discounts;
        // Now, both this.products and this.wishlist are populated.
        this.checkAndFetchImages();
      });
    }

    checkAndFetchImages() {
        this.discounts.forEach(data => {
            let product = data.product;
          // Fetch the product image from Google Pexel server
          this.imageSearchClient.getProductImage(product.name).subscribe(image => {
            product.image_src = image.photos[0].src.medium;
          });

          // Check if product is in discounts
          const isProductInDiscounts = this.discounts.some(discount => discount.product.id === product.id);
          if (isProductInDiscounts) {
            let discounted_price = this.discounts.filter(disc=> disc.product.id === product.id)[0].discount_price;
            product.discount = discounted_price;
            product.final_price = (product.price - product.discount);
          }else{
            product.final_price = product.price;
          }
        });
        this.spinner = false;
      }

}