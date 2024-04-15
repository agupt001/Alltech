import { Component, ElementRef, Inject, OnInit, Renderer2 } from "@angular/core";
import { AppService } from "../../app.service";
import { Products } from "../../http requests/products";
import { ImageSearchService } from "../../image-search.service";
import { Wishlist } from "../../http requests/wishlist";
import { forkJoin } from "rxjs";
import { Cart } from "../../http requests/cart";
import { AllProducts } from "../../http requests/all-products";
import { CartCount } from "../../http requests/cart-count";
import { CartCountService } from "../cart/cart-count.service";
import { Discounts } from "../../http requests/discounts";
import { DOCUMENT } from "@angular/common";
import { HandleToken } from "../../http requests/handle-token";

@Component({
    selector: "user-products",
    templateUrl: "products.component.html",
    styleUrls: ["products.component.css"],
    standalone: true
})
export class UserProducts implements OnInit{

    spinner: Boolean = false;
    products = [...AllProducts.allProducts];
    wishlist: Wishlist[] = [];
    cart: Cart[] = [];
    discounts: Discounts[]= [];

    originalProducts = [...AllProducts.allProducts];
    isAscending: Boolean = false;
    categories: Set<string> = new Set<string>();
    filterIcon = "bi bi-sort-alpha-down";

    private styleTag!: HTMLStyleElement;
    userId = HandleToken.userId; // Change this from login page

    constructor(private client: AppService, private cartCountService: CartCountService,
        private el: ElementRef, private renderer: Renderer2, @Inject(DOCUMENT) private document: Document){ }
    
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
            this.checkWishlist();
          });
    }

    checkWishlist() {

        this.products.forEach(product => {

          // Check if product is in wishlist
          const isProductInWishlist = this.wishlist.some(wishlistItem => wishlistItem.id === product.id);
          product.wishlist = false;
          product.inCart = false;
          if (isProductInWishlist) {
            product.wishlist = isProductInWishlist;
          }

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

          this.categories.add(product.category);
          
        });
        this.spinner = false;
      }
      
    wishlistClick(product:Products){
        
        let requestBody={
            "user_id": this.userId,
            "product_id": product.id
        };

        if (product.wishlist) {
            product.wishlist = false;
            this.client.deleteUserWishlist(requestBody).subscribe(data=> {
                // Display a toast
                console.log("deleted", data);
            });
        }else{
            product.wishlist = true;
            this.client.createUserWishlist(requestBody).subscribe(data=> {
                // Display a toast
                console.log("created", data);
            });
        }
    }

    cartBtnClick(product: Products, event: MouseEvent){
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
        
        this.addToCartAnimation(event);
    }

    addToCartAnimation(event: MouseEvent) {
        const productButtonElement = event.target as HTMLElement;
        const iconElement = productButtonElement.querySelector('.btn-icon');
        const cartIconElement = document.querySelector('.cart-icon'); // Replace '.cart-icon' with the actual selector for your cart icon
      
        if (cartIconElement && productButtonElement) {
          const productButtonRect = productButtonElement.getBoundingClientRect();
          const cartIconRect = cartIconElement.getBoundingClientRect();
      
          const translateX = cartIconRect.left - productButtonRect.left;
          const translateY = cartIconRect.top - productButtonRect.top;
          
        this.createKeyframesAnimation(translateX, translateY);
          const clone = productButtonElement.cloneNode(true) as HTMLElement;
    
          // Remove all child elements except for the icon
    Array.from(clone.childNodes).forEach(child => {
        if (!(child instanceof HTMLElement && child.classList.contains('btn-icon'))) {
          clone.removeChild(child);
        }
      });

        // Position the clone over the original button
        this.renderer.setStyle(clone, 'width', '40px'); // Adjust size as needed
        this.renderer.setStyle(clone, 'height', '40px'); // Equal to width for a circle
        this.renderer.setStyle(clone, 'border-radius', '50%');
        this.renderer.setStyle(clone, 'position', 'absolute');
        this.renderer.setStyle(clone, 'left', `${productButtonRect.left}px`);
        this.renderer.setStyle(clone, 'top', `${productButtonRect.top}px`);
        this.renderer.setStyle(clone, 'z-index', '9999');
        this.renderer.setStyle(clone, 'animation', 'moveInParabola 2s forwards');
        this.renderer.setStyle(clone, 'animation-timing-function', 'cubic-bezier(0.5, -0.5, 0.5, 1.5)');
    
        // Append the clone to the body and add the animation class
        document.body.appendChild(clone);
    
        // Clean up the clone after the animation is done
        clone.addEventListener('animationend', () => {
          document.body.removeChild(clone);
          if (this.styleTag) {
            this.renderer.removeChild(this.document.head, this.styleTag);
          }
          this.cartCountService.updateCartCount(CartCount.cart_item_number);
        });
        }
      }

      createKeyframesAnimation(xaxis:any, yaxis:any): void {
        const style = this.renderer.createElement('style');
        
        this.renderer.setAttribute(style, 'type', 'text/css');
        this.renderer.appendChild(this.document.head, style);
    
        const keyframes = `
          @keyframes moveInParabola {
            0% {
              transform: translate(0, 0);
              opacity: 1;
            }
            100% {
              transform: translate(${xaxis}px, ${yaxis}px);
              opacity: 0.4;
            }
          }
        `;
    
        this.renderer.appendChild(style, this.renderer.createText(keyframes));
        this.styleTag = style;
      }

      sort(){
        this.isAscending = !this.isAscending;
        if(this.isAscending){
          this.filterIcon = "bi bi-sort-alpha-down";
        }else{
          this.filterIcon = "bi bi-sort-alpha-up";
        }
        
        this.products.sort((a, b) => {
          return this.isAscending ? a.name.localeCompare(b.name) : b.name.localeCompare(a.name);
        });
      }

      reset(){
        this.products = [...this.originalProducts];
      }

      filter(event: any){
        let selectedCategory = event.target.value;
        
        if(selectedCategory){
          if (selectedCategory === "all") {
            this.reset();
          } else {
            this.products = this.originalProducts.filter(prod=> prod.category === selectedCategory);
          }
        }
        
      }
      
}