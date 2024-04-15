import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Products } from './http requests/products';
import { Wishlist } from './http requests/wishlist';
import { Cart } from './http requests/cart';
import { Discounts } from './http requests/discounts';
import { SalesRequest } from './http requests/sales-request';
import { SalesResponse } from './http response/sales-response';
import { UsersResponse } from './http response/users-response';
import { UserCredentials, User } from './http requests/user';
import { DiscountDml } from './http requests/discount-dml';
import { Observable } from 'rxjs';
import { HandleToken } from './http requests/handle-token';

@Injectable({providedIn: 'root'})
export class AppService {

    private url = "http://localhost:9090/";
    private microservices = {
        "products": "shop-products-service/api/products",
        "wishlist": "shop-wishlist-service/api/wishlist",
        "discounts": "shop-discounts-service/api/discounts",
        "cart": "shop-cart-service/api/cart",     
        "sales": "shop-sales-service/api/sales",
        "user": "shop-user-service/api/user",
        "admin": "shop-admin-service/api/admin",
        "auth": "shop-auth-service/auth"        
    };

    constructor(private httpClient: HttpClient) { }

    /** Login Requests */
    
    /** Register User Request */
    registerUser(user: UserCredentials){
        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.auth}`+
        `/register`, user);
    }

    getToken(request: any): Observable<string>{
        return this.httpClient.post<string>(`${this.url}`+
        `${this.microservices.auth}`+
        `/token`, request, {
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            }),
            responseType: 'text' as 'json'  // This tells HttpClient to treat the response as plain text
        });
    }


    /** User Server Requests */

    /** Products Server Request */
    getAllProducts(){
        return this.httpClient.get<Products[]>(`${this.url}`+
        `${this.microservices.products}`+
        `/getall`);
    }

    /** Wishlist Server Request Start */
    getUserWishlist(user_id: any){
        return this.httpClient.get<Wishlist[]>(`${this.url}`+
        `${this.microservices.wishlist}`+
        `/getbyuserid/${user_id}`);
    }

    deleteUserWishlist(requestBody: any){
        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.wishlist}`+
        `/delete`, requestBody);
    }

    createUserWishlist(requestBody: any){
        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.wishlist}`+
        `/create`, requestBody);
    }

    /** Wishlist Server Request End */

    /** Cart Server Request Start */
    createUserCart(requestBody: any): Observable<any>{

        // Prepare headers
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${HandleToken.token}`  // Append the token as Bearer
        });

        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.cart}`+
        `/create`, requestBody, { headers });
    }

    getUserCart(user_id: any){
        return this.httpClient.get<Cart[]>(`${this.url}`+
        `${this.microservices.cart}`+
        `/getbyuserid/${user_id}`);
    }

    updateUserCart(requestBody: any){
        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.cart}`+
        `/update`, requestBody);
    }

    deleteUserCart(requestBody: any){
        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.cart}`+
        `/delete`, requestBody);
    }

    /** Cart Server Request End */

    /** Discounts Server Request */
    getUserDiscounts(user_id: any){
        return this.httpClient.get<Discounts[]>(`${this.url}`+
        `${this.microservices.discounts}`+
        `/getbyuserid/${user_id}`);
    }

    /** Sales Server Request Start */
    createUserSale(requestBody: any){
        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.sales}`+
        `/create`, requestBody);
    }

    createUserSaleWithoutDiscount(requestBody: any){
        return this.httpClient.post<any>(`${this.url}`+
        `${this.microservices.sales}`+
        `/createwithoutdiscount`, requestBody);
    }

    /** Sales Server Request End */

    /** Admin Server Requests */

    /**** Sales server requests ****/
    getAdminAllSales(){
        return this.httpClient.get<SalesResponse[]>(`${this.url}`+
        `${this.microservices.sales}`+
        `/getall`);
    }

    /**** User server requests start ****/
    userReadAll(){
        return this.httpClient.get<UsersResponse[]>(`${this.url}`+
        `${this.microservices.user}`+
        `/getall`);
    }

    userGetById(user_id: any){
        return this.httpClient.get<UsersResponse>(`${this.url}`+
        `${this.microservices.user}`+
        `/getbyid/${user_id}`);
    }

    userCreate(requestBody: any){
        return this.httpClient.post<User>(`${this.url}`+
        `${this.microservices.user}`+
        `/create`, requestBody);
    }

    userUpdate(user_id: any,requestBody: any){
        return this.httpClient.post<User>(`${this.url}`+
        `${this.microservices.user}`+
        `/update/${user_id}`, requestBody);
    }

    userDelete(user_id: any){
        return this.httpClient.get<any>(`${this.url}`+
        `${this.microservices.user}`+
        `/delete/${user_id}`);
    }

    /**** User server requests end ****/

    /**** Admin server requests start ****/
    adminReadAll(){
        return this.httpClient.get<UsersResponse[]>(`${this.url}`+
        `${this.microservices.admin}`+
        `/getall`);
    }

    adminGetById(user_id: any){
        return this.httpClient.get<UsersResponse>(`${this.url}`+
        `${this.microservices.admin}`+
        `/getbyid/${user_id}`);
    }

    adminCreate(requestBody: any){
        return this.httpClient.post<User>(`${this.url}`+
        `${this.microservices.admin}`+
        `/create`, requestBody);
    }

    adminUpdate(user_id: any,requestBody: any){
        return this.httpClient.post<User>(`${this.url}`+
        `${this.microservices.admin}`+
        `/update/${user_id}`, requestBody);
    }

    adminDelete(user_id: any){
        return this.httpClient.get<any>(`${this.url}`+
        `${this.microservices.admin}`+
        `/delete/${user_id}`);
    }

    /**** Admin server requests end ****/

    /** Products Server Request start */
    productsCreate(requestBody: any){
        return this.httpClient.post<Products>(`${this.url}`+
        `${this.microservices.products}`+
        `/create`, requestBody);
    }

    productsGetById(product_id: any){
        return this.httpClient.get<Products>(`${this.url}`+
        `${this.microservices.products}`+
        `/getbyid/${product_id}`);
    }

    productsUpdate(product_id: any,requestBody: any){
        return this.httpClient.post<Products>(`${this.url}`+
        `${this.microservices.products}`+
        `/update/${product_id}`, requestBody);
    }

    productsDelete(product_id: any){
        return this.httpClient.get<any>(`${this.url}`+
        `${this.microservices.products}`+
        `/delete/${product_id}`);
    }

    /**** Products server requests end ****/

    /** Discounts Server Request start */

    discountsReadAll(){
        return this.httpClient.get<DiscountDml[]>(`${this.url}`+
        `${this.microservices.discounts}`+
        `/getall`);
    }

    discountsGetById(discount_id: any){
        return this.httpClient.get<DiscountDml>(`${this.url}`+
        `${this.microservices.discounts}`+
        `/getbyid/${discount_id}`);
    }

    discountsCreate(requestBody: any){
        return this.httpClient.post<DiscountDml>(`${this.url}`+
        `${this.microservices.discounts}`+
        `/create`, requestBody);
    }

    discountsUpdate(requestBody: any){
        return this.httpClient.post<DiscountDml>(`${this.url}`+
        `${this.microservices.discounts}`+
        `/update`, requestBody);
    }

    discountsDelete(requestBody: any){
        return this.httpClient.post<DiscountDml>(`${this.url}`+
        `${this.microservices.discounts}`+
        `/delete`, requestBody);
    }

    /** Discounts Server Request end */
}