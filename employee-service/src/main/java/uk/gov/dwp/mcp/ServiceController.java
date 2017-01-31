package uk.gov.dwp.mcp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@RequestMapping("/")
	public String home() {
		return "Hello Docker World!";
	}
}
