import { Component, OnDestroy, OnInit } from "@angular/core";
import { Book } from "../book";
import { BookService } from "../app.service";
import { ActivatedRoute } from "@angular/router";
import { CommonModule } from "@angular/common";
import { ModalService } from "../modal.service";
import { Subscription } from "rxjs";

@Component({
    selector: "readbyid-component",
    templateUrl: "readbyid.component.html",
    standalone: true,
    imports: [CommonModule]
})
export class ReadByIdComponent implements OnInit, OnDestroy{

    showModal = false;
    private subscription!: Subscription;

    book: Book = new Book();
    id: any;

    constructor(private service: BookService, private modalService: ModalService){ }

    ngOnInit(): void {
        
        this.subscription = this.modalService.showModal$.subscribe(data =>{
            this.showModal = data.showRead;
            this.id = data.id;
            
            if(this.id){
                this.service.getBookById(this.id).subscribe(data => {
                    this.book = data;
                });
            }
        });
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    closeModal(){
        this.modalService.closeModal();
    }

}