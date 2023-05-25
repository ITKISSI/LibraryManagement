package ma.ehei.gi4.librarymanagementapi.service;

import lombok.extern.slf4j.Slf4j;
import ma.ehei.gi4.librarymanagementapi.model.Book;
import ma.ehei.gi4.librarymanagementapi.repository.BookRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImp(BookRepository bookRepository)
    {
        this.bookRepository=bookRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book get(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book add(final Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book edit(String isbn,Book updatedBook) {

        Book existingBook = bookRepository.findByIsbn(isbn);
        if (existingBook != null) {

            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());

            Book savedBook = bookRepository.save(existingBook);
            return savedBook;
        }

        return null;

    }

    @Override
    public void delete(String isbn) {
        try {
            bookRepository.delete(bookRepository.findByIsbn(isbn));

        } catch (final EmptyResultDataAccessException ex) {
            log.debug("Attempted to delete non-existing book", ex);
        }
    }

}


