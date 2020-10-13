package gary.springframework.bulletin.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserReq {

    private String account;

    private String password;

}
