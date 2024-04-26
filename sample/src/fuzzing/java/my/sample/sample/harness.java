package my.sample.sample;

// import com.fasterxml.jackson.core.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import my.sample.sample.controller.BookController;
import my.sample.sample.dto.Book;
import my.sample.sample.service.BookServiceImpl; 

public class harness {
    public static void main(String args[]) {
        ObjectMapper objectMapper = new ObjectMapper();
        BookController obj = new BookController();
        switch(args[1]) {
            case "1":
                Book book;
                try {
                    book = objectMapper.readValue(new File(args[0]), Book.class);
                    System.out.println(book);
                } catch (IOException ioe) {
                    System.out.println(ioe);
                    return;
                }
                obj.addBook(book);
                break;
            case "2":
                long id;
                try {
                    id = objectMapper.readValue(new File(args[0]), long.class);
                    System.out.println(id);
                } catch (IOException ioe) {
                    System.out.println(ioe);
                    return;
                }
                obj.geBookById(0);
                break;
            default:
                System.out.println("wrong test number, possible values are `1` and `2` in the second parameter");
        }
    } 

    
    public static void fuzzerTestOneInput(FuzzedDataProvider fuzzedDataProvider) {
        ObjectMapper objectMapper = new ObjectMapper();
        BookController obj = new BookController();
        Book book;
        String content = fuzzedDataProvider.consumeRemainingAsAsciiString();
        try {
            book = objectMapper.readValue(content, Book.class); 
        } catch (IOException ioe) {
            System.out.println(ioe);
            return;
        }
        obj.addBook(book);
    }
}
