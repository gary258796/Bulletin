package gary.springframework.bulletin.models.request;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

/**
 * 登入頁面傳送帳號密碼用
 */
@Data
@ToString
public class UserReq implements Serializable {

    private String account;

    private String email;

    private String password;

}
