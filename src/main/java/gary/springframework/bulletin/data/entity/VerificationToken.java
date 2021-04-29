package gary.springframework.bulletin.data.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "VERIFICATION_TOKEN")
public class VerificationToken implements Serializable {

    /*時效設定為一天*/
    private final int EXPIRATION = 60 * 24 ;

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


    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    /**
     * 加上現在時將往後一天的期限
     * @param expiryTimeInMinutes
     * @return
     */
    private Date calculateExpiryDate(int expiryTimeInMinutes){
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime()); // current time
        cal.add(Calendar.MINUTE, expiryTimeInMinutes); // add expiryTime
        return new Date(cal.getTime().getTime());
    }
}
