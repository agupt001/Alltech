import { Component, OnInit } from "@angular/core";
import { Wishlist } from "../../http requests/wishlist";
import { AppService } from "../../app.service";
import { ImageSearchService } from "../../image-search.service";
import { Products } from "../../http requests/products";
import { forkJoin } from "rxjs";
import { Cart } from "../../http requests/cart";
import { CartCountService } from "../cart/cart-count.service";
import { CartCount } from "../../http requests/cart-count";
import { Discounts } from "../../http requests/discounts";
import { HandleToken } from "../../http requests/handle-token";

@Component({
    selector: "user-wishlist",
    templateUrl: "wishlist.component.html",
    standalone: true
})
export class UserWishlist implements OnInit{
    
    spinner: Boolean = false;
    wishlist: Wishlist[] = [];
    cart: Cart[] = [];
    discounts: Discounts[]= [];
    userId = HandleToken.userId; // Change this from login page

    constructor(private client: AppService, private imageSearchClient: ImageSearchService
      , private cartCountService: CartCountService){ }
    
    ngOnInit(): void {
      this.spinner = true;
      forkJoin({
        wishlist: this.client.getUserWishlist(this.userId),
        cart: this.client.getUserCart(this.userId),
        discounts: this.client.getUserDiscounts(this.userId)
      }).subscribe(({ wishlist, cart, discounts }) => {
        this.wishlist = wishlist;
        this.cart = cart;
        this.discounts = discounts;
        // Now, both this.products and this.wishlist are populated.
        this.checkWishlistAndFetchImages();
      });
    }

    checkWishlistAndFetchImages() {
        this.wishlist.forEach(product => {
          // Fetch the product image from Google Pexel server
          this.imageSearchClient.getProductImage(product.name).subscribe(image => {
            product.image_src = image.photos[0].src.large;
            
          });

          // Check if product is in cart
          const isProductInCart = this.cart.some(cartItem => cartItem.id === product.id);
          if (isProductInCart) {
            product.inCart = isProductInCart;
          }

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

      wishlistClick(product:Wishlist){
        
        let requestBody={
            "user_id": this.userId,
            "product_id": product.id
        };

        this.client.deleteUserWishlist(requestBody).subscribe(data=> {
          // Display a toast
          console.log("deleted", data);

          // Refresh data
          this.wishlist = this.wishlist.filter(wishlist => wishlist.id !== product.id);
        });

        
        
    }

      cartBtnClick(product: Wishlist){
        let requestBody={
            "userId": this.userId,
            "product_id": product.id,
            "quantity": 1
        };

        if (product.inCart) {
            product.inCart = false;
            CartCount.cart_item_number--;
            this.client.deleteUserCart(requestBody).subscribe(data=>{
                // Show toast here
                console.log(data);
            });
        } else {
            product.inCart = true;
            CartCount.cart_item_number++;
            this.client.createUserCart(requestBody).subscribe(data=>{
                // Show toast here
                console.log(data);
            });
        }

        this.cartCountService.updateCartCount(CartCount.cart_item_number);
    }
}