package gary.springframework.bulletin.models.dto;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 註冊頁面傳送帳號密碼用
 */
@Data
@ToString
public class UserRegistDto implements Serializable {

    @NotNull
    @NotEmpty
    private String account;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

}

