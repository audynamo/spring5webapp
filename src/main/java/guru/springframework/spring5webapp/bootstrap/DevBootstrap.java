package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");

        Publisher p1 = new Publisher("Harper Collins", "New York");
        Publisher p2 = new Publisher("Worx", "Los Angles");
        publisherRepository.save(p1);
        publisherRepository.save(p2);

        Book b = new Book("Domain Driven Design", "1234", p1);
        eric.getBooks().add(b);
        b.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(b);


        Author rod = new Author("Rod", "Johnson");
        Book b2 = new Book("J2EE Development", "2345", p2);
        rod.getBooks().add(b2);
        b2.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(b2);
    }
}
