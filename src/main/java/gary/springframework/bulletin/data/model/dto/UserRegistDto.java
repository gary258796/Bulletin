package gary.springframework.bulletin.data.model.dto;

import gary.springframework.bulletin.normalstuff.validations.FieldMatch;
import gary.springframework.bulletin.normalstuff.validations.ValidEmail;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 註冊頁面傳送帳號密碼用
 */
@Data
@ToString
@FieldMatch(first = "password", second = "matchingPassword", message = "{Passwords.not.matched}")
public class UserRegistDto implements Serializable {

    @NotNull
    @Size(min = 5, message = "{Size.must.greater}")
    private String userName;


    @NotNull
    @ValidEmail
    @Size(min = 3, message = "{Size.must.greater}")
    private String email;


    @NotNull
    @Size(min = 5, message = "{Size.must.greater}")
    private String password;


    @NotNull
    @Size(min = 5, message = "{Size.must.greater}")
    private String matchingPassword;

}

