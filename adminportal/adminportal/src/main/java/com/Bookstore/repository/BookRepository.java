package com.Bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>
{

}
