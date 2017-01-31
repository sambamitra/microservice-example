package uk.gov.dwp.mcp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@GetMapping("/available")
	public String available() {
		return "Spring in Action";
	}

	@GetMapping("/checked-out")
	public String checkedOut() {
		return "Spring Boot in Action";
	}

}