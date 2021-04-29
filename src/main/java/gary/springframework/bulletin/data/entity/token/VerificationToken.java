package gary.springframework.bulletin.data.entity.token;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.token.common.TokenBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "VERIFICATION_TOKEN")
public class VerificationToken extends TokenBase implements Serializable {

    public VerificationToken(final String token) {
        super();
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final User user) {
        super();
        this.token = token;
        this.userID = user.getId();
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "USER_ID")
    private int userID;

//    public void updateToken(final String token) {
//        this.token = token;
//        this.expiryDate = calculateExpiryDate(EXPIRATION);
//    }
}
