package africa.semicolon.uberdeluxe.data.dtos.requests.DriverRequest;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetDriverIdRequest {
   private Long DriverId;
    private JsonPatch updatePayLoad;

}
