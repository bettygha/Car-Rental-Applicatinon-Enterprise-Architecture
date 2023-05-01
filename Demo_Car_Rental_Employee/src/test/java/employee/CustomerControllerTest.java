package employee;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {

    private static final String BASE_URL = "http://localhost:8082";
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Test
    public void testAddCustomer() {
        // use RestAssured to send requests and validate responses
        // example:
        given()
                .contentType("application/json")
                .body("{\"customerNumber\": \"4\", \"name\": \"Kalkidan\", \"email\": \"Betelehm@Gmail.com\"}")
                .when()
                .post(BASE_URL + "/customer/addNew")
                .then()
                .statusCode(200);
    }
}