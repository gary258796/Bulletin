package gary.springframework.bulletin.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ROLE")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

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
