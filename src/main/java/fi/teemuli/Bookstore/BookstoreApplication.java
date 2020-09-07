package fi.teemuli.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.teemuli.Bookstore.domain.Book;
import fi.teemuli.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookdemo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("A farewell to Arms", "Ernst Hemingway", "12332323-21", 1929));
			repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945));

		};
	}

}
