// import com.fasterxml.jackson.core.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import my.sample.sample.controller.BookController;
import my.sample.sample.dto.Book;
import my.sample.sample.service.BookServiceImpl; 

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import com.code_intelligence.jazzer.api.FuzzerSecurityIssueHigh;

public class HarnessFuzzing {
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
