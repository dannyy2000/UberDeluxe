package africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDriverRequest {
    private String name;
    private String email;
    private String password;


}
