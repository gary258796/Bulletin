package gary.springframework.bulletin.data.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PRIVILEGE")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVILEGE_ID")
    private int privilegeID;

    /** 權限名稱 */
    @Column(name = "PRIVILEGE_NAME")
    private String privilegeName;

}
