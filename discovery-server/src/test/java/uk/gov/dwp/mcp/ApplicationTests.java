package uk.gov.dwp.mcp;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "baseUrl: http://localhost:", "location.admin: /env",
		"location.catalog: /eureka/apps" }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Value("${location.admin}")
	private String adminLocation;

	@Value("${baseUrl}")
	private String baseUrl;

	@Value("${location.catalog}")
	private String catalogLocation;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void adminLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(buildUrl(this.adminLocation), Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void catalogLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(buildUrl(this.catalogLocation), Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	private String buildUrl(final String location) {
		return new StringBuilder().append(this.baseUrl).append(this.port).append(location).toString();
	}

}
