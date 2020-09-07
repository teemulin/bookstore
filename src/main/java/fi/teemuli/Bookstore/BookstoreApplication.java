package fi.teemuli.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.teemuli.Bookstore.domain.Book;
import fi.teemuli.Bookstore.domain.BookRepository;
import fi.teemuli.Bookstore.domain.Category;
import fi.teemuli.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookdemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Sci-fi"));
			crepository.save(new Category("Self-help"));
			
			brepository.save(new Book("A bloody book", "Michael Michelsson", "43521-2", 1983, crepository.findByName("Horror").get(0)));
			brepository.save(new Book("A farewell to Arms", "Ernst Hemingway", "12332323-21", 1929, crepository.findByName("Self-help").get(0)));
			brepository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, crepository.findByName("Horror").get(0)));
		};
	}

}
