package africa.semicolon.uberdeluxe.Controller;
import africa.semicolon.uberdeluxe.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.ApiResponse;
import africa.semicolon.uberdeluxe.data.dtos.response.passengerResponse.RegisterPassengerResponse;
import africa.semicolon.uberdeluxe.service.PassengerService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/passenger")
@AllArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterPassengerRequest request){
        RegisterPassengerResponse registerResponse = passengerService.register(request);
        return ResponseEntity.status(registerResponse.getCode()).body(registerResponse);
    }

    @GetMapping("{passengerId}")
    public ResponseEntity<?> getPassengerById(@PathVariable Long passengerId){
        var foundPassenger = passengerService.getPassengerById(passengerId);
        return ResponseEntity.status(HttpStatus.OK).body(foundPassenger);
    }

    @PatchMapping(value = "{passengerId}",consumes ="application/json-patch+json")
    public ResponseEntity<?> updatePassenger(@PathVariable Long passengerId, @RequestBody JsonPatch updatePatch){
        try{
            var response = passengerService.updatePassenger(passengerId, updatePatch);
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }

    @DeleteMapping("{passengerId}")
    public ResponseEntity<?> deletePassenger(@PathVariable Long passengerId){
        passengerService.deletePassenger(passengerId);
        return ResponseEntity.ok("Passenger Deleted Successfully");
    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity<?> getAllPassengers(@PathVariable int pageNumber){
        var response = passengerService.getAllPassenger(pageNumber);
        return ResponseEntity.ok(response.getContent());
    }


}

