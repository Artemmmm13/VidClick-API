package com.api.vidclick;

import com.api.vidclick.DTO.RegisterRequest;
import com.api.vidclick.DTO.SignUpResponse;
import com.api.vidclick.services.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@SpringBootTest
class VidClickApplicationTests {

    @Autowired
    private AuthenticationService authService;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnCreatedWhenValidUserDataIsSent(){
        RegisterRequest validRequest =  new RegisterRequest();
        validRequest.setName("Chuck Berry");
        validRequest.setPassword("A12@NAkosl!sQ");
        validRequest.setEmail("email@gmail.com");
        validRequest.setCreatorProfileImage("/home/godzilla/Pictures/Screenshots/Screenshot from 2023-04-07 17-23-02.png");

        // Test the register method
        ResponseEntity<SignUpResponse> response = authService.register(validRequest, mock(HttpServletResponse.class));

        // Assert that the response is as expected
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void shouldNotReturnCreatedWhenInValidUserDataIsSent(){
        RegisterRequest invalidRequest =  new RegisterRequest();
        invalidRequest.setName("Chuck5");
        invalidRequest.setPassword("A12@NAkosl!sQ");
        invalidRequest.setEmail("ema@gmail.com");
        invalidRequest.setCreatorProfileImage("/home/godzilla/Pictures/Screenshots/Screenshot from 2023-04-07 17-23-02.png");

        // Assert that the response is as expected
        assertThrows(IllegalArgumentException.class, ()-> authService.register(invalidRequest, mock(HttpServletResponse.class)));
    }

}
