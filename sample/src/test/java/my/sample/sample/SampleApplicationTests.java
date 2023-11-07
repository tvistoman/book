package my.sample.sample;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import my.sample.sample.dto.Book;

@SpringBootTest
class SampleApplicationTests {

	/**
	 * 
	 */
	@Test
	void contextLoads() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Book book = objectMapper.readValue(new File("src/test/resources/json_car.json"), Book.class);
			System.out.println(book);
		} catch (IOException ioe) {
			System.out.println(ioe);
			// assert(false);
			return;
		}
	}

}
