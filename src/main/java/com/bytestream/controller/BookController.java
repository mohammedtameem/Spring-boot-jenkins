package com.bytestream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytestream.entity.AddResponse;
import com.bytestream.entity.Book;
import com.bytestream.service.BookService;

@RestController
@RequestMapping("api/v1")
public class BookController 
{
	@Autowired
	BookService service;
	
	@GetMapping("/getBooks")
	public List<Book> getBooks()
	{
		return service.getBooks();
	}
	
	
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id)
	{
		try
		{
			Book book =service.getBookById(id);
			return new ResponseEntity<Book>(book,HttpStatus.OK);
		}
		catch(Exception ex)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBookByName")
	public Book getBookByName(@RequestParam("name") String name)
	{
		return service.getBookByName(name);
	}
	
	
	@GetMapping("/getSortedBookName")
	public List getSortedBookName()
	{
		return service.getBooksSortedByAuthor();
	}
	
	@PostMapping("/addBook")
	public Book addBook(@RequestBody Book book)
	{
		return service.addBook(book);
	
	}
	
	@PutMapping("/updateBook")
	public Book updateBook(@RequestBody Book book)
	{
		return service.updateBook(book);
	
	}
	
	@DeleteMapping("/delete/{id}")
	public AddResponse deleteBook(@PathVariable("id") int id)
	{
		return service.deleteBook(id);
	}
 	
	
	
	
}
