package africa.semicolon.uberdeluxe.data.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {

    private String houseNumber;
    private String street;
    private String city;
    private String state;
}
