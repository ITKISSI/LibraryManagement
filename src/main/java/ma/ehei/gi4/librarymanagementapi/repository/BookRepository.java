package ma.ehei.gi4.librarymanagementapi.repository;

import ma.ehei.gi4.librarymanagementapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    public Book findByIsbn(String isbn);
    public void deleteByIsbn(String isbn);
}
