package com.example.assignment9.controller;

import com.example.assignment9.entity.Book;
import com.example.assignment9.exception.BookException;
import com.example.assignment9.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
@Autowired
private BookException bookException;
    @Autowired
    private BookService bookService;

    @GetMapping("/getbooks")
    public List<Book> getallbooks(){
        List L2=new ArrayList<>();
        L2 = bookService.getallbooks();
        return L2;
    }
    @PostMapping("/addbooks")
    public String create(@RequestBody Book B1){
       return bookService.addbook(B1);
    }
    @GetMapping("getbookbyid/{id}")
    public Book getbyid(@PathVariable long id){
        Optional<Book> OBJ1=bookService.getbookbyid(id);
        return OBJ1.get();

    }
    @DeleteMapping("deletebook/{id}")
    public String deletebook(@PathVariable long id){
      return bookService.deletebook(id);

    }
    @PutMapping("modifybook/{id}")
    public void modify(@RequestBody Book B2,@PathVariable long id){
        Optional<Book> Obj=bookService.getbookbyid(id);
        try{
            Book B3=Obj.get();
            bookService.modifybook(B2,B3);

        }catch(Exception e){
            ResponseEntity<Map<String, Object>> RE1= bookException.handleException(e);
            System.out.println(RE1);
        }
    }



}

