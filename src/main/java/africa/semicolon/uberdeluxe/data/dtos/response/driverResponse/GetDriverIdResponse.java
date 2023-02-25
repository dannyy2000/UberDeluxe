package africa.semicolon.uberdeluxe.data.dtos.response.driverResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetDriverIdResponse {
    private String name;
    private String email;
    private String password;

}
