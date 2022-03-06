import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
public class PublisherSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherSpringApplication.class, args);
	}

}
