package com.example.library.services;

import com.example.library.models.Book;
import com.example.library.repositories.BookRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Resource
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void editBook(Book book, Integer id) throws Exception {
        Book dbBook = bookRepository.findById(id).orElse(null);
        if (dbBook == null) {
            throw new Exception("bad id");
        }
        dbBook.setDescription(book.getDescription());
    }

    public void deleteBook(Integer id) throws Exception {
        Book dbBook = bookRepository.findById(id).orElse(null);
        if (dbBook == null) {
            throw new Exception("invalid id");
        }
        bookRepository.delete(dbBook);
    }
}
