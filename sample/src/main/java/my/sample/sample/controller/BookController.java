package my.sample.sample.controller;

import java.util.ArrayList; 
import java.util.HashSet;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RestController; 

import my.sample.sample.dto.Book; 

import my.sample.sample.service.BookServiceImpl; 

@RestController
public class BookController { 

	@Autowired
	BookServiceImpl bookServiceImpl; 

	public  BookController() {
		bookServiceImpl = new BookServiceImpl();
	}

	@PostMapping("/") 
	public void addBook(@RequestBody Book book) { 
		bookServiceImpl.addBook(book); 
	} 

	@PostMapping("/request") 
	public void addBook(RequestEntity<String> requestEntity, @RequestBody Book book) { 
		// bookServiceImpl.addBook(book);
		System.out.println(requestEntity.toString());
	} 
	
	/*@PostMapping("/servlet") 
	public void addBook(HttpServletResponse httpServletResponse, @RequestBody Book book) { 
		System.out.println(httpServletResponse.toString());
	}*/

	@PostMapping("/servlet") 
	public void addBook(HttpServletRequest request, @RequestBody Book book) { 
		HttpServletRequestWrapper rw = new HttpServletRequestWrapper(request);
		ObjectMapper obj = new ObjectMapper();
		byte[] s = new byte[300];
		try {
			s = obj.writeValueAsBytes(rw);
			// File f = new File("/home/t/request.txt");
			// f.setWritable(true);
			FileOutputStream os = new FileOutputStream("/home/t/request.txt");
			os.write(s);
			os.close();
			// System.err.println(f.getPath());
		} catch(Exception e) {System.err.println(e);}

		System.out.println(s);
	}

	@GetMapping("/findall") 
	public HashSet<Book> getAllBook() { 
		return bookServiceImpl.findAllBook(); 
	} 

	@GetMapping("/findbyid/{id}") 
	public Book geBookById(@PathVariable long id) { 
		return bookServiceImpl.findBookByID(id); 
	} 

	@DeleteMapping("/delete") 
	public void deleteBook() { 
		bookServiceImpl.deleteAllData(); 
	} 

}
