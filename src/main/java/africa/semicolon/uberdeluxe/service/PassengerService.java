package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.RegisterResponse;

public interface PassengerService {

    RegisterResponse register(RegisterPassengerRequest registerRequest);
}
