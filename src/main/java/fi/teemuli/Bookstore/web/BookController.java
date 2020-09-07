package fi.teemuli.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.teemuli.Bookstore.domain.Book;
import fi.teemuli.Bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	//This is to inject CRUD functionality from BookRepository to Controller here
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String indexGreeting() {
		return "index";
	}
	
	//Listing functionality
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//Add functionality
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	//Saving added info
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	//Delete functionality
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookID, Model model) {
		repository.deleteById(bookID);
		return "redirect:../booklist";
	}

}
