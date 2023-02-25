package africa.semicolon.uberdeluxe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"africa.semicolon.uberdeluxe.cloud"})
public class UberDeluxeApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberDeluxeApplication.class, args);
	}

}
