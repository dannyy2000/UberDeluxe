package africa.semicolon.uberdeluxe.Controller;

import africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.ApiResponse;
import africa.semicolon.uberdeluxe.data.dtos.response.driverResponse.RegisterDriverResponse;
import africa.semicolon.uberdeluxe.data.dtos.response.passengerResponse.RegisterPassengerResponse;
import africa.semicolon.uberdeluxe.service.DriverService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/api/v1/driver")
//public class DriverController {
//
//    private final DriverService driverService;

//    public DriverController(DriverService driverService) {
//        this.driverService = driverService;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> register(@RequestBody RegisterDriverRequest request){
//        RegisterDriverResponse registerResponse = driverService.register(request);
//        return ResponseEntity.status(registerResponse.getCode()).body(registerResponse);
//    }

//    @GetMapping("{driverId}")
//    public ResponseEntity<?> getDriverById(@PathVariable Long driverId){
//        var foundDriver = driverService.getDriverById(driverId);
//        return ResponseEntity.status(HttpStatus.OK).body(foundDriver);
//    }
//    @PatchMapping(value = "{driverId}",consumes ="application/json-patch+json")
//    public ResponseEntity<?> updateDriver(@PathVariable Long driverId, @RequestBody JsonPatch updatePatch){
//        try{
//            var response = driverService.updateDriver(driverId, updatePatch);
//            return  ResponseEntity.status(HttpStatus.OK).body(response);
//        }catch (Exception exception){
//            return ResponseEntity.badRequest().body(exception.getMessage());
//        }
//
//    }

//    @DeleteMapping("{driverId}")
//    public ResponseEntity<?> deleteDriver(@PathVariable Long driverId){
//        driverService.deleteDriver(driverId);
//        return ResponseEntity.ok(ApiResponse.builder().message("Driver Deleted Successfully"));
//    }


//}
