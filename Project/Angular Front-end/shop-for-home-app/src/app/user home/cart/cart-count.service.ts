import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AppService } from '../../app.service';

@Injectable({providedIn: 'root'})
export class CartCountService {

    private cartCount = new BehaviorSubject<number>(0);
    currentCartCount = this.cartCount.asObservable();

    constructor() { }

    updateCartCount(count: number): void{
        this.cartCount.next(count);
    }
    
}