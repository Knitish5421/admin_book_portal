package com.Bookstore.service.impl;


import java.util.List;

import com.Bookstore.model.Book;

public interface BookService
{
   Book save(Book book);
   List<Book> findAll();
   Book findById(Long id);
   void delete(Long id);

}
