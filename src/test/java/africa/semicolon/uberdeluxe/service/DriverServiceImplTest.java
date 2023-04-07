package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.driverRequest.RegisterDriverRequest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class  DriverServiceImplTest {
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

//    @Test
//    public void registerDriverTest() throws IOException {
//        MockMultipartFile file =
//                new MockMultipartFile("test_license",
//                        new FileInputStream("C:\\Users\\user\\Downloads\\balabalu.jpeg"));
//        var response = driverService.register(registerDriverRequest,file);
//        assertThat(response).isNotNull();
//        assertThat(response.isSuccessful()).isTrue();
//        assertThat(response.getCode()).isEqualTo(HttpStatus.CREATED.value());
//    }




}