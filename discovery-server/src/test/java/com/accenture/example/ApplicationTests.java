package com.accenture.example;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.accenture.example.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@TestPropertySource(properties = { "baseUrl: http://localhost:", "location.admin: /env",
		"location.catalog: /eureka/apps" })
public class ApplicationTests {

	@Value("${location.admin}")
	private String adminLocation;

	@Value("${baseUrl}")
	private String baseUrl;

	@Value("${location.catalog}")
	private String catalogLocation;

	@Value("${local.server.port}")
	private int port;

	@Test
	public void adminLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(buildUrl(this.adminLocation), Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void catalogLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(buildUrl(this.catalogLocation), Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	private String buildUrl(final String location) {
		return new StringBuilder().append(this.baseUrl).append(this.port).append(location).toString();
	}

}
