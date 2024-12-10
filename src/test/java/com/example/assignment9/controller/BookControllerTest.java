package com.example.assignment9.controller;

import com.example.assignment9.entity.Book;
import com.example.assignment9.exception.BookException;
import com.example.assignment9.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
     @Mock
    private BookService bookService;
    @InjectMocks
    private BookController bookController;
    @Mock
    private BookException bookException;


    static List<Book> L1=new ArrayList<>();
    @BeforeAll
    static void creation(){
        Book B1=new Book();
        B1.setId(1);
        B1.setTitle("Book1");
        B1.setAuthor("Kalyan");
        B1.setPrice(new BigDecimal(55));
        B1.setPublishedDate(LocalDate.now());
        L1.add(B1);
    }

    @Test
    void getallbooks() {
        when(bookService.getallbooks()).thenReturn(L1);
        List<Book> result=bookController.getallbooks();
        Assertions.assertEquals(1,result.size());
        Assertions.assertEquals("Book1",result.get(0).getTitle());
    }

    @Test
    void create() {
        Book B1=L1.get(0);
        when(bookService.addbook(B1)).thenReturn("Success");
String result=bookController.create(B1);
           Assertions.assertEquals("Success",result);
    }

    @Test
    void getbyid() {
        when(bookService.getbookbyid(1L)).thenReturn(Optional.of(L1.get(0)));
        Book B2=bookController.getbyid(1L);
        Assertions.assertEquals("Book1",B2.getTitle());
    }

    @Test
    void deletebook() {
         when(bookService.deletebook(1L)).thenReturn("Success");
         String result1=bookController.deletebook(1L);
        Assertions.assertEquals("Success",result1);

    }

    @Test
    void modify() {
        Book B1=L1.get(0);
        Book B2=new Book();
        B2.setId(4);
        B2.setTitle("BookNumber4");
        B2.setAuthor("Sathvik");
        B2.setPrice(new BigDecimal(100));
        B2.setPublishedDate(LocalDate.now());
        doNothing().when(bookService).modifybook(B2,B1);
        bookController.modify(B2, 1L);
        when(bookService.getbookbyid(4L)).thenReturn(Optional.of(L1.get(0)));
        Book B4=bookController.getbyid(4L);
        Assertions.assertEquals("BookNumber4",B4.getTitle());

    }
}