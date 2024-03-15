import { Component, OnDestroy, OnInit } from "@angular/core";
import { Book } from "../book";
import { BookService } from "../app.service";
import { CommonModule } from "@angular/common";
import { Subscription } from "rxjs";
import { ModalService } from "../modal.service";
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
    selector: "update-component",
    templateUrl: "update.component.html",
    styleUrl: "update.component.css",
    standalone: true,
    imports: [CommonModule, FormsModule]
})
export class UpdateComponent implements OnInit, OnDestroy{

    showModal = false;
    private subscription!: Subscription;

    book: Book = new Book();
    id: any;

    constructor(private service: BookService, private modalService: ModalService, private route: Router){ }

    ngOnInit(): void {
        
        this.subscription = this.modalService.showModal$.subscribe(data =>{
            this.showModal = data.showUpdate;
            this.id = data.id;

            if(this.id){
                this.service.getBookById(this.id).subscribe(data => {
                    this.book = data;
                });
            }
        });
    }

    updateBook(form: any){

        if(form.valid){
            this.service.updateBook(this.id, this.book).subscribe(data => {

                this.modalService.notifyBookUpdate(data);
                this.closeModal();
                
            });
        }
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }

    closeModal(){
        this.modalService.closeModal();
    }


}