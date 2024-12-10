package com.example.assignment9.service;

import com.example.assignment9.entity.Book;
import com.example.assignment9.exception.BookException;
import com.example.assignment9.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookException bookException;

    @Autowired
    private BookRepository bookRepository;

    public String addbook(Book B1){
        B1.setPublishedDate(LocalDate.now());
        bookRepository.save(B1);
        return "Success";
    }
    public  List<Book> getallbooks(){
        List<Book> L1=new ArrayList<>();
        L1=bookRepository.findAll();
        return L1;
    }
    public Optional<Book> getbookbyid(long id){
        Optional<Book> B2=bookRepository.findById(id);
        return B2;
    }
    public String deletebook(long id){
            bookRepository.deleteById(id);
            return "Success";

    }
    public void modifybook(Book B2,Book B3){
        B3.setAuthor(B2.getAuthor());
        B3.setPrice(B2.getPrice());
        B3.setTitle(B2.getTitle());
        if(B2.getPublishedDate()==null) {
            B3.setPublishedDate(LocalDate.now());
        }
        bookRepository.save(B3);
    }

}
