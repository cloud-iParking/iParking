package ro.ubb.cloud.iParking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class IParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(IParkingApplication.class, args);
	}

}
