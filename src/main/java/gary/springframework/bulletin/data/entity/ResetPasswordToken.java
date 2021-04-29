package gary.springframework.bulletin.data.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "RESET_PASSWORD_TOKEN")
public class ResetPasswordToken implements Serializable {

    /** 過期期限 - 兩小時 */
    private static final int EXPIRATION = 60 * 2 ;

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

    /** Methods */
    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
//
//    public void updateToken(final String token) {
//        this.token = token;
//        this.expiryDate = calculateExpiryDate(EXPIRATION);
//    }

}
