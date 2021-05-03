package gary.springframework.bulletin.data.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "USER_ROLES")
public class UserRoles implements Serializable {

    @Column(name = "USER_ID")
    private int userID;

    @Column(name = "ROLE_ID")
    private int roleID;

}
