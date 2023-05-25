package ma.ehei.gi4.librarymanagementapi.service;

import ma.ehei.gi4.librarymanagementapi.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {

    public List<Book> getAll();
    public Book get(String isbn);
    public Book add(Book book);
    public Book edit(String isbn,Book book);
    public void delete(String isbn);
}
