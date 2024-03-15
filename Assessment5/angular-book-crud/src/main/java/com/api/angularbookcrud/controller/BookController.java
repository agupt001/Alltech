package com.api.angularbookcrud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.angularbookcrud.entity.Book;
import com.api.angularbookcrud.request.BookRequest;
import com.api.angularbookcrud.response.BookResponse;
import com.api.angularbookcrud.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService service;
	
	@PostMapping("/create")
	public BookResponse createBook(@RequestBody BookRequest book) {
		return service.createBook(book);
	}
	
	@GetMapping("/getbyid/{id}")
	public BookResponse getBookById(@PathVariable int id) {
		return service.getBookById(id);
	}
	
	@GetMapping("/getall")
	public List<Book> getAllBook(){
		return service.getAllBook();
	}
	
	@PostMapping("/update/{id}")
	public BookResponse updateBook(@PathVariable int id, @RequestBody Book newBook) {
		return service.updateBook(id, newBook);
	}
	
	@GetMapping("/delete/{id}")
	public Map<String, Boolean> deleteBook(@PathVariable int id){
		return service.deleteBook(id);
	}
	
}
