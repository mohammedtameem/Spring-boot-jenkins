package com.bytestream.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytestream.entity.AddResponse;
import com.bytestream.entity.Book;
import com.bytestream.repository.BookRepo;

@Service
public class BookService 
{
	
	@Autowired
	BookRepo bookRepo;
	
	Comparator<Book> sortByAuthor = (b1,b2)->b1.getAuthor().compareTo(b2.getAuthor());
	
	public List<Book> getBooks()
	{
		 return bookRepo.findAll();
	}
	
	public Book getBookById(int id)
	{
	//	return bookRepo.findById(id).get();
		return null;
	}
	
	
	public Book getBookByName(String name)
	{
		List<Book> books = bookRepo.findAll();
		return books.stream()
				.filter(book -> book.getName()
						.equals(name))
							.findFirst()
								.get();
	}
	
	public List getBooksSortedByAuthor()
	{
		
		List<Book> books = bookRepo.findAll();
		books.sort(sortByAuthor);
		return books;

	}
	
	public Book addBook(Book book)
	{
		book.setId(getMaxId());
		return bookRepo.save(book);
		
	}
	
	public Book updateBook(Book book)
	{
		bookRepo.save(book);
		return book;
	}
	
	public AddResponse deleteBook(int id)
	{
		bookRepo.deleteById(id);
		AddResponse response = new AddResponse();
		response.setMsg("deleted the record successfully");
		response.setId(id);
		return response;
	}
	
	public int getMaxId()
	{
		return bookRepo.findAll().size() + 1;
	}
}
