package com.oracle.graalvm.benchmarks.jibber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JibberHttpRequestTest {

	final String protocol = "http://";
	final String host = "localhost";
	final String endpoint = "/jibber";

    @LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String URL;

	@BeforeEach
	void setupTest() {
		// Endpoint
		this.URL = protocol + host + ":" + port + endpoint;
	}

	@Test
	public void shouldReturnOK() throws Exception {
		// Make a request
		ResponseEntity r = restTemplate.getForEntity(URL, String.class);
		System.out.println(r);

		assertEquals(HttpStatus.OK, r.getStatusCode(), "Request did not return OK");
	}

	@Test
	public void shouldReturnNoneEmptyString() throws Exception {
		// Make a request
		ResponseEntity r = restTemplate.getForEntity(URL, String.class);
		System.out.println(r);

		assertNotNull(r.getBody().toString(), "Content returned from URL was null");
		assertTrue(r.getBody().toString().length() > 0, "Returned poem was empty");
	}
}
