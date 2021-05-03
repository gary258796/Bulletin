package gary.springframework.bulletin.data.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "ROLE_PRIVILEGE")
public class RolePrivilege implements Serializable {

    @Column(name = "ROLE_ID")
    private int roleID;

    @Column(name = "PRIVILEGE_ID")
    private int privilegeID;

}
