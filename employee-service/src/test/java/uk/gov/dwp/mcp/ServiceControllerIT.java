package uk.gov.dwp.mcp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, classes = { RestClientConfig.class,
		ApplicationConfig.class })
public class ServiceControllerIT {

	@Autowired
	private Environment env;

	@Autowired
	private RestOperations restOperations;

	@Test
	public void getEmployeById() {
		// Given
		// When
		final ResponseEntity<String> response = this.restOperations
				.getForEntity(getEmployeeServiceApiEndpoint() + "/1234", String.class);
		// Then
		assertEquals("Response status not as expected", HttpStatus.OK, response.getStatusCode());
		assertEquals("Response not as expected", "Hello Docker World!", response.getBody());
	}

	private String getEmployeeServiceApiEndpoint() {
		return this.env.getProperty("service.api.url");
	}

}
