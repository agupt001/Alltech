import { Component, OnInit } from '@angular/core';
import { Cart } from '../../http requests/cart';
import { AppService } from '../../app.service';
import { ImageSearchService } from '../../image-search.service';
import { forkJoin } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CartCountService } from './cart-count.service';
import { CartCount } from '../../http requests/cart-count';
import { Discounts } from '../../http requests/discounts';
import {
  SalesRequest,
  SalesRequestWithoutDiscount,
} from '../../http requests/sales-request';
import { HandleToken } from '../../http requests/handle-token';

@Component({
  selector: 'user-cart',
  templateUrl: 'cart.component.html',
  standalone: true,
  imports: [FormsModule],
})
export class UserCart implements OnInit {
  spinner: Boolean = false;
  cart: Cart[] = [];
  discounts: Discounts[] = [];
  total_value = 0;
  userId = HandleToken.userId; // Change this from login page

  constructor(
    private client: AppService,
    private imageSearchClient: ImageSearchService,
    private cartCountService: CartCountService
  ) {}

  ngOnInit(): void {
    this.spinner = true;
    forkJoin({
      cart: this.client.getUserCart(this.userId),
      discounts: this.client.getUserDiscounts(this.userId),
    }).subscribe(({ cart, discounts }) => {
      this.cart = cart;
      this.discounts = discounts;
      // Now, both this.products and this.wishlist are populated.
      this.checkAndFetchImages();
    });
  }

  checkAndFetchImages() {
    this.cart.forEach((product) => {
      // Fetch the product image from Google Pexel server
      this.imageSearchClient
        .getProductImage(product.name)
        .subscribe((image) => {
          product.image_src = image.photos[0].src.medium;
        });

      // Check if product is in discounts
      const isProductInDiscounts = this.discounts.some(
        (discount) => discount.product.id === product.id
      );
      if (isProductInDiscounts) {
        let discounted_price = this.discounts.filter(
          (disc) => disc.product.id === product.id
        )[0].discount_price;
        let discounted_id = this.discounts.filter(
          (disc) => disc.product.id === product.id
        )[0].id;
        product.discount = discounted_price;
        product.discount_id = discounted_id;
        product.final_price = product.price - product.discount;
      } else {
        product.final_price = product.price;
      }
    });
    this.spinner = false;
  }

  shop() {
    let buyingItems = this.cart.filter((cart) => cart.selected);
    // Store the buying items in stock rest api
    console.log('Items bought-> ', buyingItems);

    buyingItems.forEach((prod) => {
      let salesRequest;
      let final_buying_price = prod.final_price * prod.quantity;
      if (prod.discount_id) {
        salesRequest = new SalesRequest(
          this.userId,
          prod.id,
          prod.name,
          prod.category,
          prod.discount_id,
          prod.price,
          prod.discount,
          final_buying_price,
          prod.quantity
        );

        console.log('salesRequest', salesRequest);

        this.client.createUserSale(salesRequest).subscribe((data) => {
          // Display toast here
          console.log(data);
        });
      } else {
        salesRequest = new SalesRequestWithoutDiscount(
          this.userId,
          prod.id,
          prod.name,
          prod.category,
          prod.price,
          final_buying_price,
          prod.quantity
        );

        console.log('salesRequest', salesRequest);

        this.client
          .createUserSaleWithoutDiscount(salesRequest)
          .subscribe((data) => {
            // Display toast here
            console.log(data);
          });
      }
    });

    buyingItems.forEach((cartItem) => {
      CartCount.cart_item_number--;
      let requestBody = {
        userId: this.userId,
        product_id: cartItem.id,
      };
      this.client.deleteUserCart(requestBody).subscribe((data) => {
        console.log(data);
      });
    });
    this.cartCountService.updateCartCount(CartCount.cart_item_number);
    this.cart = this.cart.filter((cart) => !cart.selected);
    this.calculateTotalValue();
  }

  deleteFromCart(cartItem: Cart) {
    CartCount.cart_item_number--;
    this.cart = this.cart.filter((cart) => cart.id !== cartItem.id);
    this.cartCountService.updateCartCount(CartCount.cart_item_number);
    this.calculateTotalValue();
    let requestBody = {
      userId: this.userId,
      product_id: cartItem.id,
    };
    this.client.deleteUserCart(requestBody).subscribe((data) => {
      console.log(data);
    });
  }

  updateQuantity(cartItem: Cart) {
    console.log(cartItem.quantity);
    let requestBody = {
      userId: this.userId,
      product_id: cartItem.id,
      quantity: cartItem.quantity,
    };
    this.client.updateUserCart(requestBody).subscribe((data) => {
      this.calculateTotalValue();
      console.log(data);
    });
    
  }

  calculateTotalValue() {
    this.total_value = 0;
    this.cart.forEach(item => {
      if (item.selected) {
        this.total_value += (item.final_price * item.quantity);
      }
    });
  }
}
