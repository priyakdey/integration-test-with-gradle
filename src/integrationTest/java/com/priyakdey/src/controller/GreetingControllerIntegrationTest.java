package com.priyakdey.src.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.net.Inet4Address;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Priyak Dey
 */
@DisplayName("INTEGRATION TEST - GreetingControllerTest")
@Tag("non-unit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("Should return 200 OK")
    @Test
    void testGreet() throws Exception {
        final var hostname = Inet4Address.getLocalHost().getHostAddress();
        final var url = "http://" + hostname + ":" + port + "/greetings";

        final var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.ACCEPT, "application/json");

        final var requestEntity = new HttpEntity<Void>(null, headers);
        final var responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        assertAll(
                () -> assertEquals(200, responseEntity.getStatusCodeValue()),
                () -> assertEquals("Howdy stranger, how are you doing today?", responseEntity.getBody())
        );
    }

}
