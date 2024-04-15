export class SalesRequest{
    userId: any;
	product_id: any;
    product_name: any;
    product_category: any;
	discount_id: any = null;
	product_price: any;
	discount: number = 0;
	final_price: any;
	quantity: any;

    constructor(userId: any, 
        product_id: any,
        product_name: any,
    product_category: any,
        discount_id: any,
        product_price: any,
        discount: number,
        final_price: any,
        quantity: any){

            this.userId= userId;
            this.product_id= product_id;
            this.product_name= product_name;
            this.product_category= product_category;
            this.discount_id= discount_id ? discount_id: null;
            this.product_price= product_price;
            this.discount= discount!=0 ? discount: 0;
            this.final_price= final_price;
            this.quantity= quantity;

    }
}

export class SalesRequestWithoutDiscount{
    userId: any;
	product_id: any;
    product_name: any;
    product_category: any;
	product_price: any;
	final_price: any;
	quantity: any;

    constructor(userId: any, 
        product_id: any,
        product_name: any,
    product_category: any,
        product_price: any,
        final_price: any,
        quantity: any){

            this.userId= userId;
            this.product_id= product_id;
            this.product_name= product_name;
            this.product_category= product_category;
            this.product_price= product_price;
            this.final_price= final_price;
            this.quantity= quantity;

    }
}

