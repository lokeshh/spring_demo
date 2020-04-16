package com.example.library.controllers;

import com.example.library.models.Book;
import com.example.library.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/book")
    public ResponseEntity addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.created(null).build();
    }

    @PostMapping("/book/{id}")
    public ResponseEntity<?> addBook(@RequestBody Book book, @PathVariable Integer id) {
        try {
            bookService.editBook(book, id);
            return ResponseEntity.ok().body("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok().body("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }
}
