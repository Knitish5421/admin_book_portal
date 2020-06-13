package com.Bookstore.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Bookstore.model.Book;
import com.Bookstore.service.impl.BookService;

@Controller
@RequestMapping("/book")
public class BookController
{
	@Autowired
	BookService bookService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String adddBookPost(Model model)
	{
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String adddBook(@ModelAttribute("book") Book book, HttpServletRequest request)
	{
		bookService.save(book);
		// uploading image
		MultipartFile bookImage = book.getBookImage();
		try
		{
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";// rename book with id
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/img/book/" + name)));
			stream.write(bytes);
			stream.close();
			
			
			/*
			// saving image in Table
			String imgName= book.getId()+"";
			ClassPathResource backImgFile = new ClassPathResource("bookImage/"+imgName+"."+"png");
			byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
			backImgFile.getInputStream().read(arrayPic);
			Book blackImage = new Book(imgName, "png", arrayPic);
			bookService.save(blackImage);
			//
			// retrieve image from Table
			for(Book imageModel : bookService.findAll()){
				if(imageModel.getImgName()!=null)
				{
				Files.write(Paths.get("retrieve-dir/" + imageModel.getImgName() + "." + imageModel.getImgType()), imageModel.getPic());
				}
			}
			
			*/
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/book/bookList";

	}

	@RequestMapping("/bookList")
	public String bookList(Model model)
	{
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "bookList";
	}
	
	@RequestMapping(value="/bookInfo")
	public String bookInfo(Model model, @RequestParam("id") Long id )
	{
		Book book= bookService.findById(id);
		model.addAttribute("book", book);
		return "bookInfo";
	}
	
	@RequestMapping(value="/updateBook")
	public String updateBook(Model model, @RequestParam("id") Long id )
	{
		Book book= bookService.findById(id);
		model.addAttribute("book", book);
		return "updateBook";
	}
	
	@RequestMapping(value="/updateBook",  method = RequestMethod.POST)
	public String updateBookPost(Model model, @ModelAttribute("book") Book book, HttpServletRequest request )
	{
		bookService.save(book);
		
		MultipartFile bookImage = book.getBookImage();
		
		if(!bookImage.isEmpty())
		{
			try
			{
				byte[] bytes = bookImage.getBytes();
				String name = book.getId() + ".png";
				Files.delete(Paths.get("src/main/resources/static/img/book/" + name)); //to delete exit image
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/img/book/" + name)));
				stream.write(bytes);
				stream.close();
			}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		}
		return "redirect:/book/bookInfo?id="+book.getId();
	}
	
	@RequestMapping(value="/removBook")
	public String removeBook(Model model, @RequestParam("id") Long bookId, HttpServletRequest request )
	{
		bookService.delete(bookId);
		
			try
			{
				String name = bookId + ".png";
				Files.delete(Paths.get("src/main/resources/static/img/book/" + name)); //to delete exit image
				
			}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "redirect:/book/bookList";
	}

}
