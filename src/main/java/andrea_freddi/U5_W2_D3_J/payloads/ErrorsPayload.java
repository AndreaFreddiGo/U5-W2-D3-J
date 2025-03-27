package andrea_freddi.U5_W2_D3_J.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;
}
