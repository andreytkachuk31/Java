package service;

import entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Cacheable("books")
    public Book findBook(String name, String isbn) {
        return new Book(name, isbn);
    }
}
