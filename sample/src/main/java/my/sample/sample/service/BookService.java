package my.sample.sample.service;

import java.util.HashSet; 
import my.sample.sample.dto.Book; 

public interface BookService { 
	HashSet<Book> findAllBook(); 
	Book findBookByID(long id); 
	void addBook(Book b); 
	void deleteAllData(); 
}
