package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.driverResponse.RegisterDriverResponse;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.data.models.Driver;
import africa.semicolon.uberdeluxe.data.models.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class DriverServiceImplTest {
@Autowired
    private DriverService driverService;

    private RegisterDriverRequest registerDriverRequest;


    @BeforeEach
    void setUp(){
        registerDriverRequest = new RegisterDriverRequest();
        registerDriverRequest.setEmail("@Tomiwa");
        registerDriverRequest.setName("Akinsanya");
        registerDriverRequest.setPassword("tommy");
    }

    @Test
    public void registerDriverTest() throws IOException {
        MockMultipartFile file =
                new MockMultipartFile("test_license",
                        new FileInputStream("C:\\Users\\user\\Downloads\\balabalu.jpeg"));
        var response = driverService.register(registerDriverRequest,file);
        assertThat(response).isNotNull();
        assertThat(response.isSuccessful()).isTrue();
        assertThat(response.getCode()).isEqualTo(HttpStatus.CREATED.value());
    }




}