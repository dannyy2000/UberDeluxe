package africa.semicolon.uberdeluxe.data.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequest {
    private final Sender sender = new Sender("uber_deluxe","noreply@uberdeluxe.net");
    private List<Recipient> to;
    private final String subject = "Welcome to uber_deluxe";
    private final String htmlContent = "<p>Hello, welcome to uber deluxe</p";
}
