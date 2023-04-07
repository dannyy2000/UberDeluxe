package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.BookRideRequest;
import africa.semicolon.uberdeluxe.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.ApiResponse;
import africa.semicolon.uberdeluxe.data.dtos.response.passengerResponse.RegisterPassengerResponse;
import africa.semicolon.uberdeluxe.data.models.Passenger;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PassengerService {

    RegisterPassengerResponse register(RegisterPassengerRequest registerRequest);

    Passenger getPassengerById(Long userId);

    Optional<Passenger> getPassengerBy(Long passengerId);

    Passenger updatePassenger(Long passengerId, JsonPatch updatePayLoad);

    void savePassenger(Passenger passenger);

    Page<Passenger> getAllPassenger(int pageNumber);

    void deletePassenger(Long id);

    ApiResponse bookRide(BookRideRequest bookRideRequest);


}
