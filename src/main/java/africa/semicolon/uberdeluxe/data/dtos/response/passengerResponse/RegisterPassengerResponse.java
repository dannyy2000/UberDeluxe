package africa.semicolon.uberdeluxe.data.dtos.response.passengerResponse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPassengerResponse {
    private String message;
     private int code;
    private boolean isSuccessful;
    private Long id;


}
