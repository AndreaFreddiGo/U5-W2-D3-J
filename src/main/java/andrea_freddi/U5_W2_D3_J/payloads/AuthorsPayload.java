package andrea_freddi.U5_W2_D3_J.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthorsPayload {
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
}
