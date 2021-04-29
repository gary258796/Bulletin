package gary.springframework.bulletin.data.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 *  使用者註冊時,使用之Entity
 */
@Data
@Entity
@Table(name = "USER")
public class User implements Serializable {

    public User() {
        super();
        this.userEnabled = false ; // 一開始建立都是為FALSE,通過Email驗證之後會設定為true
    }

    /** 使用者ID(編號) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    /** 使用者帳號 */
    @Column(name = "USER_NAME")
    private String userName;

    /** 使用者信箱 */
    @Column(name = "USER_EMAIL")
    private String userEmail;

    /**
     *  使用者密碼
     *  因為是存放Bcrypt編碼過的, 產生的字串大小就是60
     */
    @Column(name = "USER_PASSWORD", length = 60)
    private String userPassword;

    /** 是否已通過驗證 */
    @Column(name = "USER_ENABLED")
    private Boolean userEnabled;

}
