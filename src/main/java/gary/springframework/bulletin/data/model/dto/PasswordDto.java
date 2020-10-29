package gary.springframework.bulletin.data.model.dto;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class PasswordDto {

    @NotNull
    @Size(min = 5, message = "{Size.must.greater}")
    private String password;


    @NotNull
    @Size(min = 5, message = "{Size.must.greater}")
    private String matchingPassword;

    @NotNull
    private String token;
}
