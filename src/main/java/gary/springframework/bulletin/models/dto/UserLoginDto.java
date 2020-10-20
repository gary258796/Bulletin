package gary.springframework.bulletin.models.dto;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登入傳送帳號密碼用
 */
@Data
@ToString
public class UserLoginDto implements Serializable {

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String password;

}
