package gary.springframework.bulletin.data.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private int roleID;

    @Column(name = "ROLE_NAME")
    private String roleName;

}
