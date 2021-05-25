package com.springboot.practice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.practice.entities.Book;
import com.springboot.practice.service.BookService;

@RestController
public class MyController {

	@Autowired
	private BookService bookService;
	
	/*
	 * @GetMapping("/books") public Book getBooks() { Book book = new Book();
	 * book.setId(123); book.setTitle("Git And Github");
	 * book.setAuthor("Karunmanchi"); book.setPages(350); return book; }
	 */
	
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getBooks()
	{
		List<Book> book = bookService.getAllBook();
		if(book.size() <= 0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id)
	{
		Book book =  bookService.getBookById(id);
		
		if(book == null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	@PostMapping("/books")
	public ResponseEntity<Book> addBooks(@RequestBody Book book)
	{
		Book bk = null;
		try {
		bk = bookService.addBook(book);
		return ResponseEntity.of(Optional.of(bk));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id)
	{
		try {
		bookService.deleteBook(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id)
	{
		try {
		bookService.updateBook(book, id);
		return ResponseEntity.ok().body(book);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
