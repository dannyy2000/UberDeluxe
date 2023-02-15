package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.RegisterResponse;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class PassengerServiceImplTest {
    @Autowired
    private  PassengerService passengerService;

    @BeforeEach
    void setUp(){
    }

    @Test
    void registerTest(){
        RegisterPassengerRequest request = new RegisterPassengerRequest();
        request.setEmail("@Dan");
        request.setPassword("tom");
        request.setName("Daniel");

        RegisterResponse registerResponse = passengerService.register(request);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getCode())
                .isEqualTo(HttpStatus.CREATED.value());
    }

}