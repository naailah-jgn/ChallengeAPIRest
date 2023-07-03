package fr.wcs.ChallengeAPIRest.controllers;

import fr.wcs.ChallengeAPIRest.entities.Book;
import fr.wcs.ChallengeAPIRest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTitle = body.get("title");
        String searchDescription = body.get("description");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTitle, searchDescription);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        Book bookToUpdate = bookRepository.findById(id).get();
        if (bookToUpdate != null) {
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setDescription(book.getDescription());
            return bookRepository.save(bookToUpdate);
        }else{
            return null;
        }
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}


