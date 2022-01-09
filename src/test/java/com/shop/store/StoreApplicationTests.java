package com.shop.store;

import com.shop.store.entity.Role;
import com.shop.store.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StoreApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddUserSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/registration/";
        URI uri = new URI(baseUrl);
        User User = new User("Adam", "Gilly", "123", "test@email.com", Role.USER);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<User> request = new HttpEntity<>(User, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testAddUserMissingHeader() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/registration/";
        URI uri = new URI(baseUrl);
        User User = new User("Adam", "Gilly", "123", "test@email.com", Role.USER);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(User, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        assertEquals(400, result.getStatusCodeValue());
        assertEquals(true, result.getBody().contains("Missing request header"));
    }
}