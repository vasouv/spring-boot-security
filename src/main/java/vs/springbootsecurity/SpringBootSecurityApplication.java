package vs.springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringBootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

}

@RestController
class HomeController {

	@GetMapping("/")
	public String home() {
		return "Home Controller";
	}

}

@RestController
class UnsecuredController {

	@GetMapping("/unsecured")
	public String unsecured() {
		return "Unsecured Controller";
	}

}

@RestController
class SecuredController {

	@GetMapping("/secured")
	public String secured() {
		return "Secured Controller";
	}
}
