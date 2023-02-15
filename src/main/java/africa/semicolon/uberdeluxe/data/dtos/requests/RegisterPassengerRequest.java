package africa.semicolon.uberdeluxe.data.dtos.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterPassengerRequest {
    private String email;
    private String name;
    private String password;

}
