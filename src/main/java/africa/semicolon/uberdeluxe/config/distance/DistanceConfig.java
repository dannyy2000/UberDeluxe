package africa.semicolon.uberdeluxe.config.distance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@AllArgsConstructor
public class DistanceConfig {
    private String googleDistanceUrl;
    private String googleApiKey;
}
