package com.springboot.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Book> getBooks()
	{
		return bookService.getAllBook();
	}
	
	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable("id") int id)
	{
		return bookService.getBookById(id);
	}
	@PostMapping("/books")
	public void addBooks(@RequestBody Book book)
	{
		bookService.addBook(book);
	}
	
	@DeleteMapping("/book/{id}")
	public void deleteBook(@PathVariable("id") int id)
	{
		bookService.deleteBook(id);
	}
	
	@PutMapping("/book/{id}")
	public void updateBook(@RequestBody Book book, @PathVariable("id") int id)
	{
		bookService.updateBook(book, id);
	}
}
