package gary.springframework.bulletin.data.entity.token;

import gary.springframework.bulletin.data.entity.token.common.TokenBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "RESET_PASSWORD_TOKEN")
public class ResetPasswordToken extends TokenBase implements Serializable {

    public ResetPasswordToken() {}

    public ResetPasswordToken(String token, int userID) {
        this.token = token;
        this.userID = userID;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    /** token content */
    @Column(name = "TOKEN")
    private String token;

    /** Id of User who owns this token */
    @Column(name = "USER_ID")
    private int userID;

    /** Expire date of token */
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

//
//    public void updateToken(final String token) {
//        this.token = token;
//        this.expiryDate = calculateExpiryDate(EXPIRATION);
//    }

}
