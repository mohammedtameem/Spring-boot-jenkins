package com.bytestream.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytestream.entity.Book;
import com.bytestream.repository.BookRepo;

@SpringBootTest(classes = {BookServiceTest.class})
public class BookServiceTest 
{
	public static Logger logger = LoggerFactory.getLogger(BookServiceTest.class);
	
	@Mock
	BookRepo bookRepo;
	
	@InjectMocks
	BookService bookService;
	
	List<Book> list = new ArrayList<>();
	
	@Test
	@Order(1)
	public void test_getAllCountries()
	{
		logger.info("Test cases are executing");
		list.add(new Book(1,"java programming","mark"));
		list.add(new Book(2,"devops","john"));
		when(bookRepo.findAll()).thenReturn(list);
		assertEquals(2, bookService.getBooks().size());
		
	}

}
