import { Products } from "./products";

export class AllProducts{

    static allProducts: Products[] = [];

    constructor(products: Products[]){
        AllProducts.allProducts = products;
     };

    static getProducts(){
        return this.allProducts;
    }
}