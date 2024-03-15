package com.api.angularbookcrud.service;

import java.util.List;
import java.util.Map;

import com.api.angularbookcrud.entity.Book;
import com.api.angularbookcrud.request.BookRequest;
import com.api.angularbookcrud.response.BookResponse;

public interface BookService {

	BookResponse createBook(BookRequest book);
	BookResponse getBookById(int id);
	List<Book> getAllBook();
	BookResponse updateBook(int id, Book newBook);
	Map<String, Boolean> deleteBook(int id);
	
}
