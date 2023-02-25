package africa.semicolon.uberdeluxe.mapper;

import africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest.RegisterDriverRequest;
import africa.semicolon.uberdeluxe.data.dtos.requests.passengerRequest.RegisterPassengerRequest;
import africa.semicolon.uberdeluxe.data.models.AppUser;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParaMapper {

    public static AppUser map(RegisterPassengerRequest request){
        AppUser appUser = new AppUser();
        appUser.setName(request.getName());
        appUser.setPassword(request.getPassword());
        appUser.setEmail(request.getEmail());

        return appUser;
    }

//    public static AppUser map(RegisterDriverRequest request){
//        AppUser appUser = new AppUser();
//        appUser.setName(request.getName());
//        appUser.setPassword(request.getPassword());
//        appUser.setEmail(request.getEmail());
//
//        return appUser;
//    }
}
