package za.co.librarrymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibrarrymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarrymanagementApplication.class, args);
	}

}
