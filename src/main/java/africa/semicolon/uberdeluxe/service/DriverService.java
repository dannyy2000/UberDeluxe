package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest.GetDriverIdRequest;
import africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.driverResponse.RegisterDriverResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DriverService {

     RegisterDriverResponse register(RegisterDriverRequest registerDriverRequest, MultipartFile licenseImage);

//     Driver getDriverById(Long id);
//
//     Driver updateDriver(Long driverId,JsonPatch updatePayLoad);
//
//     void deleteDriver(Long id );
}
