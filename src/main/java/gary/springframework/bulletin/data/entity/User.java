package gary.springframework.bulletin.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 *  使用者註冊時,使用之Entity
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String userName;

    private String email;

    private String password;

    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {
        super();
        this.enabled = false ; // 一開始建立都是為FALSE,通過Email驗證之後會設定為true
    }
}