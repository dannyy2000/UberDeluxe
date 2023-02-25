package africa.semicolon.uberdeluxe.data.dtos.response.driverResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDriverResponse {
    private String name;
    private String email;
    private String password;

}
