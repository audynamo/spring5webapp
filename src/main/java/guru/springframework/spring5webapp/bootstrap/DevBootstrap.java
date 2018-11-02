package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");

        Book b = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(b);
        b.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(b);

        Author rod = new Author("Rod", "Johnson");
        Book b2 = new Book("J2EE Development", "2345", "Worx");
        rod.getBooks().add(b2);
        b2.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(b2);
    }
}
