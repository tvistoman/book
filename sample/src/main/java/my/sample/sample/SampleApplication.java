package my.sample.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleApplication {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 8182 ); 
		SpringApplication.run(SampleApplication.class, args);
	}

}

