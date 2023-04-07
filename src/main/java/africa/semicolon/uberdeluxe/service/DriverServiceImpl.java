package africa.semicolon.uberdeluxe.service;
import africa.semicolon.uberdeluxe.AppUtilities;
import africa.semicolon.uberdeluxe.cloud.CloudService;
import africa.semicolon.uberdeluxe.data.dtos.requests.driverRequest.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dtos.requests.EmailNotificationRequest;
import africa.semicolon.uberdeluxe.data.dtos.requests.Recipient;
import africa.semicolon.uberdeluxe.data.dtos.response.driverResponse.RegisterDriverResponse;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import africa.semicolon.uberdeluxe.data.models.Driver;

import africa.semicolon.uberdeluxe.data.repositories.DriverRepository;
import africa.semicolon.uberdeluxe.exception.ImageUploadException;

import africa.semicolon.uberdeluxe.notification.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    private final CloudService cloudService;

    private final ModelMapper modelMapper;

    private final MailService mailService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterDriverResponse register(RegisterDriverRequest registerDriverRequest) {
        AppUser driverDetails = modelMapper.map(registerDriverRequest, AppUser.class);
        driverDetails.setCreatedAt(LocalDateTime.now().toString());

        var imageUrl = cloudService.upload(registerDriverRequest.getLicenseImage());
        if (imageUrl == null)
            throw new ImageUploadException("Driver Registration failed");

        Driver driver = Driver.builder()
                .userDetails(driverDetails)
                .licenseImage(imageUrl)
                .build();

        Driver savedDriver = driverRepository.save(driver);

        EmailNotificationRequest emailRequest = buildNotificationRequest(savedDriver.getUserDetails().getEmail(),savedDriver.getUserDetails().getName(), driver.getId());
        String response = mailService.sendHtmlMail(emailRequest);
        if(response == null) return getRegisterFailureResponse();

        return RegisterDriverResponse.builder()
                .code(HttpStatus.CREATED.value())
                .id(savedDriver.getId())
                .isSuccessful(true)
                .message("Driver Registration Successful")
                .build();
    }

    private static RegisterDriverResponse getRegisterFailureResponse() {
        return RegisterDriverResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .id(1L)
                .isSuccessful(false)
                .message("Driver Registration failed")
                .build();
    }

    private EmailNotificationRequest buildNotificationRequest(String email,String name, Long userId) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        request.getTo().add(new Recipient(name,email));
        String template = AppUtilities.getMailTemplate();
        String content = String.format(template,name,AppUtilities.generateVerificationLink(userId));
        request.setHtmlContent(content);
        return request;

    }

    @Override
    public Optional<Driver> getDriverBy(Long driverId) {
        return driverRepository.findById(driverId);
    }

    @Override
    public void saveDriver(Driver driver) {
       driverRepository.save(driver);
    }
}



