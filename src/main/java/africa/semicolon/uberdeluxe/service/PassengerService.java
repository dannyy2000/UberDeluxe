package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.passengerResponse.RegisterPassengerResponse;
import africa.semicolon.uberdeluxe.data.models.Passenger;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

public interface PassengerService {

    RegisterPassengerResponse register(RegisterPassengerRequest registerRequest);

    Passenger getPassengerById(Long userId);

    Passenger updatePassenger(Long passengerId, JsonPatch updatePayLoad);

    Page<Passenger> getAllPassenger(int pageNumber);

    void deletePassenger(Long id);


}
