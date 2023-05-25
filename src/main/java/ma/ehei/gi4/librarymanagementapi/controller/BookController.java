package ma.ehei.gi4.librarymanagementapi.controller;

import ma.ehei.gi4.librarymanagementapi.model.Book;
import ma.ehei.gi4.librarymanagementapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<Book> add(@RequestBody final Book book){
        final Book savedBook = bookService.add(book);
        return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);

    }
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> get(@PathVariable final String isbn) {
        final Book foundBook = bookService.get(isbn);

        if(foundBook==null)
        {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(foundBook, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<List<Book>>(bookService.getAll(), HttpStatus.OK);
    }


    @PutMapping("/{isbn}")
    public ResponseEntity edit(@PathVariable final String isbn, @RequestBody final Book book) {
        if(bookService.edit(isbn,book)!=null){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity deleteBook(@PathVariable final String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
