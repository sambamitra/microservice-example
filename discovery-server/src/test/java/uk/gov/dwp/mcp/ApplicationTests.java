package uk.gov.dwp.mcp;

import static org.junit.Assert.assertEquals;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "baseUrl: https://localhost:", "location.admin: /env",
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

	private RestTemplate testRestTemplate = new RestTemplate();

	@Test
	public void adminLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(buildUrl(this.adminLocation), Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Before
	public void before() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider)
						.setSSLSocketFactory(new SSLConnectionSocketFactory(
								new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build()))
						.build());
		this.testRestTemplate.setRequestFactory(requestFactory);

	}

	@Test
	public void catalogLoads() {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(buildUrl(this.catalogLocation), Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test(expected = ResourceAccessException.class)
	public void clientHttpRequestsAreRefused() {
		// Given
		final String requestEndpointUrl = buildUrl(this.catalogLocation);
		String httpRestEndpointUrl = requestEndpointUrl;
		if (requestEndpointUrl.startsWith("https://")) {
			// Replace https with http
			httpRestEndpointUrl = requestEndpointUrl.replaceFirst("https:", "http:");
		}

		// When
		this.testRestTemplate.getForEntity(httpRestEndpointUrl, Map.class);

		// Then - access denied
	}

	private String buildUrl(final String location) {
		return new StringBuilder().append(this.baseUrl).append(this.port).append(location).toString();
	}

}
