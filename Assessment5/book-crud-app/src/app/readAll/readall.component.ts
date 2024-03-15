import { Component, OnInit } from "@angular/core";
import { Book } from "../book";
import { BookService } from "../app.service";
import { Router, RouterLink, RouterLinkActive } from "@angular/router";
import { ModalService } from "../modal.service";
import { ReadByIdComponent } from "../readById/readbyid.component";
import { UpdateComponent } from "../update/update.component";

@Component({
    selector: "readall-component",
    templateUrl: "readall.component.html",
    standalone: true,
    imports: [RouterLink, RouterLinkActive, ReadByIdComponent, UpdateComponent]
})
export class ReadAllComponent implements OnInit{

    books!: Book[];

    openReadModalComp: Boolean = false;
    openUpdateModalComp: Boolean = false;

    constructor(private service: BookService, private router: Router, private modalService: ModalService){ }

    ngOnInit(): void {

        this.modalService.bookUpdated$.subscribe(
            updatedBook => {
                this.refreshData();
            }
        )

        this.refreshData();
    }

    refreshData(){
        this.service.getAllBooks().subscribe(data => {
            this.books = data;
        });
    }

    deleteBook(id: any){
        this.service.deleteBook(id).subscribe((data: Object)=>{
            this.books = this.books.filter(book => book.id !== id);
        });
    }

    openReadModal(id: any){
        this.openReadModalComp = true;
        this.modalService.openReadModal(id);
    }

    openUpdateModal(id: any){
        this.openUpdateModalComp = true;
        this.modalService.openUpdateModal(id);
    }

}