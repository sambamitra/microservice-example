package uk.gov.dwp.mcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

	@GetMapping("/available")
	public String available() {
		LOGGER.info("Fetching available books");
		return "Spring in Action";
	}

	@GetMapping("/checked-out")
	public String checkedOut() {
		LOGGER.info("Fetching checked out books");
		return "Spring Boot in Action";
	}

}