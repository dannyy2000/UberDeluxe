package africa.semicolon.uberdeluxe.service;
import africa.semicolon.uberdeluxe.cloud.CloudService;
import africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.driverResponse.RegisterDriverResponse;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.data.models.Driver;

import africa.semicolon.uberdeluxe.data.repositories.DriverRepository;
import africa.semicolon.uberdeluxe.exception.ImageUploadException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j

public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    private final CloudService cloudService;

    private final ModelMapper modelMapper;

    @Override
    public RegisterDriverResponse register(RegisterDriverRequest registerDriverRequest, MultipartFile licenseImage) {
        AppUser driverDetails = modelMapper.map(registerDriverRequest, AppUser.class);
        driverDetails.setCreatedAt(LocalDateTime.now().toString());

        var imageUrl = cloudService.upload(licenseImage);
        if (imageUrl == null)
            throw new ImageUploadException("Driver Registration failed");

        Driver driver = Driver.builder()
                .userDetails(driverDetails)
                .licenseImage(imageUrl)
                .build();

        Driver savedDriver = driverRepository.save(driver);

        return RegisterDriverResponse.builder()
                .code(HttpStatus.CREATED.value())
                .id(savedDriver.getId())
                .isSuccessful(true)
                .message("Driver Registration Successful")
                .build();
    }
}



