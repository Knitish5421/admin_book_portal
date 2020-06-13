package com.Bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bookstore.model.Book;
import com.Bookstore.repository.BookRepository;
@Service
public class BookServiceImp implements BookService
{
	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book)
	{
		return bookRepository.save(book);
	}

	@Override
	public List<Book> findAll()
	{
		return bookRepository.findAll();
	}

	@Override
	public Book findById(Long id)
	{
		return bookRepository.findById(id).get();
	}

	@Override
	public void delete(Long id)
	{
		bookRepository.deleteById(id);
		
	}

	

}
