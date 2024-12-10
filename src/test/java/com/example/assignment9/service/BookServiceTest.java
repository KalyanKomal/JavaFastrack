package com.example.assignment9.service;

import com.example.assignment9.entity.Book;
import com.example.assignment9.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;
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
    void addbook() {
Book B1=L1.get(0);
        when(bookRepository.save(any(Book.class))).thenReturn(B1);
        String result=bookService.addbook(B1);
        Assertions.assertEquals("Success",result);


    }

    @Test
    void getallbooks() {

        when(bookRepository.findAll()).thenReturn(L1);
        List<Book> result=bookService.getallbooks();
        Assertions.assertEquals(1,result.size());
Assertions.assertEquals("Kalyan",result.get(0).getAuthor());
          }

    @Test
    void getbookbyid() {
          when(bookRepository.findById(1L)).thenReturn(Optional.of(L1.get(0)));
          Optional<Book> OBJ1=bookService.getbookbyid(1L);
          Assertions.assertEquals("Book1",OBJ1.get().getTitle());
    }

    @Test
    void deletebook() {
         doNothing().when(bookRepository).deleteById(1L);
         String result1=bookService.deletebook(1L);
         Assertions.assertEquals("Success",result1);
    }

    @Test
    void modifybook() {
        Book B1=L1.get(0);
        Book B2=new Book();
        B2.setId(1);
        B2.setTitle("BookNumber1");
        B2.setAuthor("Komal");
        B2.setPrice(new BigDecimal(70));
        B2.setPublishedDate(LocalDate.now());
        when(bookRepository.save(any(Book.class))).thenReturn(B1);
        bookService.modifybook(B2,B1);
        Assertions.assertEquals(new BigDecimal(70),B1.getPrice());

    }
}