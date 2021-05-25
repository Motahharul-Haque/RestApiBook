package com.springboot.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.practice.entities.Book;

@Component
public class BookService {

	private static List<Book> list = new ArrayList<>();

	static {

		list.add(new Book(321, "Head First java", "Kathy Sierra", 470));
		list.add(new Book(121, "Introduction to Algorithms", "Thomas H. Cormen", 1500));
		list.add(new Book(325, "Head First Design Patterns", "Kathy Sierra", 260));
		list.add(new Book(333, "Clean Code", "Robert Cecil Martin", 500));
		list.add(new Book(222, "The Algorithm Design Manual", "Steven Skiena", 450));
		list.add(new Book(111, "Big Data Analytics with Hadoop", "Sridhar Alla", 680));

	}

	public List<Book> getAllBook() {
		return list;
	}

	public Book getBookById(int id) {
		return list.stream().filter(b -> b.getId() == id).findFirst().get();
	}

	public Book addBook(Book book) {
		list.add(book);
		return book;
	}

	public void deleteBook(int id) {

		list = list.stream().filter(book -> book.getId() != id).collect(Collectors.toList());

	}

	public Book updateBook(Book book, int id) {

		list = list.stream().map(b -> {
			if (b.getId() == id) {
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
				b.setPages(book.getPages());
			}
			return b;
		}).collect(Collectors.toList());

		return book;
	}

}
