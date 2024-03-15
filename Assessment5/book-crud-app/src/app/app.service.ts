import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BookRequest } from './request';
import { Book } from './book';

@Injectable({providedIn: 'root'})
export class BookService {
    
    private url = "http://localhost:8080/api/book";

    constructor(private httpClient: HttpClient) { }
    
    createBook(book: BookRequest){
        return this.httpClient.post<Book>(`${this.url}`+`/create`,book);
    }

    getBookById(id: any){
        return this.httpClient.get<Book>(`${this.url}`+`/getbyid/${id}`);
    }

    getAllBooks(){
        return this.httpClient.get<Book[]>(`${this.url}`+`/getall`);
    }

    updateBook(id: any, book: BookRequest){
        return this.httpClient.post<Book>(`${this.url}`+`/update/${id}`,book);
    }

    deleteBook(id: any){
        return this.httpClient.get<Object>(`${this.url}`+`/delete/${id}`);
    }
}