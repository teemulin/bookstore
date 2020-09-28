package fi.teemuli.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.teemuli.Bookstore.domain.Book;
import fi.teemuli.Bookstore.domain.BookRepository;
import fi.teemuli.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	//This is to inject CRUD functionality from BookRepository to Controller here
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	// Login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String indexGreeting() {
		return "index";
	}
	
	//Listing functionality
	@RequestMapping(value = {"/booklist"}, method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//REST service for listing all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
			return (List<Book>) repository.findAll();
	}
	
	//REST service to get book by id
	@RequestMapping (value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
	return repository.findById(bookId);
	}
	
	//Add functionality
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookID, Model model) {
		repository.deleteById(bookID);
		return "redirect:../booklist";
	}
	
	//Edit functionality
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookID, Model model) {
		model.addAttribute("book", repository.findById(bookID));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}

}
