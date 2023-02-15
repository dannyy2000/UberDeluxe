package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.RegisterResponse;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.data.models.Passenger;
import africa.semicolon.uberdeluxe.data.repositories.PassengerRepository;
import africa.semicolon.uberdeluxe.mapper.ParaMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService{


    private final PassengerRepository passengerRepository;
   // private ModelMapper modelMapper = new ModelMapper();


    @Override
    public RegisterResponse register(RegisterPassengerRequest registerRequest) {
        AppUser appUser = ParaMapper.map(registerRequest);
        appUser.setCreatedAt(LocalDateTime.now());

        Passenger passenger = new Passenger();
        passenger.setUserDetails(appUser);

        Passenger savedPassenger = passengerRepository.save(passenger);

        RegisterResponse registerResponse = getRegisterResponse(savedPassenger);

        return registerResponse;


    }

    private static RegisterResponse getRegisterResponse(Passenger savedPassenger) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setCode(HttpStatus.CREATED.value());
        registerResponse.setId(savedPassenger.getId());
        registerResponse.setSuccessful(true);
        registerResponse.setMessage("User registration successful");
        return registerResponse;
    }
}
