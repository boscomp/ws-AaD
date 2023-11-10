package proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TestApiService {
	public static void main(String[] args) {
		SpringApplication.run(TestApiService.class, args);
	}
}
