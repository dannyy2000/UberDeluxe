package africa.semicolon.uberdeluxe.Controller;
import africa.semicolon.uberdeluxe.data.dtos.requests.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.RegisterResponse;
import africa.semicolon.uberdeluxe.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/passenger")
@AllArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterPassengerRequest request){
        RegisterResponse registerResponse = passengerService.register(request);
        return ResponseEntity.status(registerResponse.getCode()).body(registerResponse);
    }

}

