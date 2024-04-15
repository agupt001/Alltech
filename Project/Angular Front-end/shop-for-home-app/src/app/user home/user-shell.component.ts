import { Component, OnInit } from "@angular/core";
import { RouterLink, RouterLinkActive, RouterOutlet } from "@angular/router";
import { Products } from "../http requests/products";
import { AppService } from "../app.service";
import { ImageSearchService } from "../image-search.service";
import { forkJoin } from "rxjs";
import { AllProducts } from "../http requests/all-products";

@Component({
    selector: "user-shell-component",
    templateUrl: "user-shell.component.html",
    standalone: true,
    imports: [RouterLink, RouterOutlet, RouterLinkActive]
})
export class UserShellComponent implements OnInit{

    spinner: Boolean = false;
    products: Products[] = [];

    constructor(private client: AppService, private imageSearchClient: ImageSearchService){ }

    ngOnInit(): void {
        this.spinner = true;

        forkJoin({
            products: this.client.getAllProducts(),
          }).subscribe(({ products }) => {
            this.products = products;
            
            // Now, both this.products and this.wishlist are populated.
            this.checkWishlistAndFetchImages();
          });
    }

    checkWishlistAndFetchImages() {

        this.products.forEach(product => {

          // Fetch the product image from Google Pexel server
          this.imageSearchClient.getProductImage(product.name).subscribe(image => {
            product.image_src = image.photos[0].src.large;
            
          });
        });
        
        new AllProducts(this.products);
        this.spinner = false;
      }

}