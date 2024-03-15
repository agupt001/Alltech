package com.api.angularbookcrud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.angularbookcrud.entity.Book;
import com.api.angularbookcrud.repository.BookRepository;
import com.api.angularbookcrud.request.BookRequest;
import com.api.angularbookcrud.response.BookResponse;

import jakarta.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repo;
	
	@Override
	@Transactional
	public BookResponse createBook(BookRequest book) {
		
		Book bkObj = new Book(book.getTitle(), book.getPrice());
		Book bk = repo.save(bkObj);
		
		return generateResponse(bk);
		
	}

	@Override
	@Transactional
	public BookResponse getBookById(int id) {
		
		Book bk = repo.findById(id).orElseThrow(()-> new RuntimeException("Book not found!"));
		return generateResponse(bk);
		
	}

	@Override
	@Transactional
	public List<Book> getAllBook() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public BookResponse updateBook(int id, Book newBook) {
		
		Book bk = repo.findById(id).orElseThrow(()-> new RuntimeException("Book not found!"));
		bk.setTitle(newBook.getTitle());
		bk.setPrice(newBook.getPrice());
		
		Book updatedBook = repo.save(bk);
		
		return generateResponse(updatedBook);
		
	}

	@Override
	@Transactional
	public Map<String, Boolean> deleteBook(int id) {
		
		Book bk = repo.findById(id).orElseThrow(()-> new RuntimeException("Book not found!"));
		
		repo.delete(bk);
		
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("book deleted", Boolean.TRUE);
		
		return response;
	}
	
	private BookResponse generateResponse(Book bk) {
		
		BookResponse response = new BookResponse();
		response.setId(bk.getId());
		response.setTitle(bk.getTitle());
		response.setPrice(bk.getPrice());
		
		return response;
		
	}

}
