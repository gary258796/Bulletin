package gary.springframework.bulletin.data.model.dto;

import gary.springframework.bulletin.normalstuff.validations.FieldMatch;
import gary.springframework.bulletin.normalstuff.validations.ValidPassword;
import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@FieldMatch(first = "password", second = "matchingPassword", message = "{Passwords.not.matched}")
public class PasswordDto {

    @NotNull
    @ValidPassword
    @Size(min = 5, message = "{Size.must.greater}")
    private String password;


    @NotNull
    @Size(min = 5, message = "{Size.must.greater}")
    private String matchingPassword;


    @NotNull
    private String token;
}
