package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.driverRequest.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.driverResponse.RegisterDriverResponse;
import africa.semicolon.uberdeluxe.data.models.Driver;

import java.util.Optional;

public interface DriverService {

     RegisterDriverResponse register(RegisterDriverRequest registerDriverRequest);

     Optional<Driver> getDriverBy(Long driverId);

     void saveDriver(Driver driver);

//     Driver getDriverById(Long id);
//
//     Driver updateDriver(Long driverId,JsonPatch updatePayLoad);
//
//     void deleteDriver(Long id );
}
