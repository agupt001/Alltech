import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { Book } from "../book";
import { BookService } from "../app.service";
import { BookRequest } from "../request";
import { Router } from "@angular/router";

@Component({
    selector: "create-component",
    templateUrl: "create.component.html",
    styleUrl: "create.component.css",
    standalone: true,
    imports: [FormsModule, CommonModule]
})
export class CreateComponent{

    book: BookRequest = new BookRequest();

    constructor(private service: BookService, private router: Router){ }

    submit(form: any){
        if(form.valid){
            this.service.createBook(this.book).subscribe(data =>{
                this.router.navigate(['/read-all-component']);
            })
        }
    }
}