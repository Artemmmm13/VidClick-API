package com.api.vidclick.packages;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class VidClickApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;
	@Test
	void contextLoads() {
	}

    @Test
    void shouldReturnFundraisingOfferWhenDataIsReceived(){
        ResponseEntity<String> response = restTemplate.getForEntity("/fundraising/1000", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1000);
    }

    @Test
    void shouldNotReturnFundraisingOfferWhenDataIsReceived(){
        ResponseEntity<String> response = restTemplate.getForEntity("/fundraising/137", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
