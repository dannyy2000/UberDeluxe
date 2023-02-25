package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.passengerResponse.RegisterPassengerResponse;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.data.models.Passenger;
import africa.semicolon.uberdeluxe.data.repositories.PassengerRepository;
import africa.semicolon.uberdeluxe.exception.BusinessLogicException;
import africa.semicolon.uberdeluxe.mapper.ParaMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class PassengerServiceImpl implements PassengerService{
    private static final int NUMBER_OF_ITEMS_PER_PAGE = 3;
    private final PassengerRepository passengerRepository;

//    private ModelMapper mapper;
   // private ModelMapper modelMapper = new ModelMapper();


    @Override
    public RegisterPassengerResponse register(RegisterPassengerRequest registerRequest) {
        AppUser appUser = ParaMapper.map(registerRequest);
        appUser.setCreatedAt(LocalDateTime.now().toString());
        Passenger passenger = new Passenger();
        passenger.setUserDetails(appUser);

        Passenger savedPassenger = passengerRepository.save(passenger);

        RegisterPassengerResponse registerResponse = getRegisterResponse(savedPassenger);

        return registerResponse;


    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(()->
                new BusinessLogicException(
                        String.format("Passenger with id %d not found",passengerId)
                ));
    }

    @Override
    public Passenger updatePassenger(Long passengerId, JsonPatch updatePayLoad) {
        ObjectMapper mapper = new ObjectMapper();
       Passenger foundPassenger =  getPassengerById(passengerId);

      JsonNode node =  mapper.convertValue(foundPassenger, JsonNode.class);

      try{
        JsonNode updateNode =   updatePayLoad.apply(node);
          var updatedPassenger =mapper.convertValue(updateNode, Passenger.class);
          updatedPassenger = passengerRepository.save(updatedPassenger);
          return updatedPassenger;

      } catch ( JsonPatchException e) {
          log.error(e.getMessage());
          throw new RuntimeException();
      }

    }

    @Override
    public Page<Passenger> getAllPassenger(int pageNumber) {
        if(pageNumber < 1) pageNumber = 0;
        else pageNumber = pageNumber - 1;
        Pageable pageable = PageRequest.of(pageNumber,NUMBER_OF_ITEMS_PER_PAGE);
        return passengerRepository.findAll(pageable);

    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    private static RegisterPassengerResponse getRegisterResponse(Passenger savedPassenger) {
        RegisterPassengerResponse registerResponse = new RegisterPassengerResponse();
        registerResponse.setCode(HttpStatus.CREATED.value());
       registerResponse.setId(savedPassenger.getId());
        registerResponse.setSuccessful(true);
        registerResponse.setMessage("User registration successful");
        return registerResponse;
    }
}
