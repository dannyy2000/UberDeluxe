package africa.semicolon.uberdeluxe.data.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRideRequest {

    private Long passengerId;
    private Location origin;
    private Location destination;
}
