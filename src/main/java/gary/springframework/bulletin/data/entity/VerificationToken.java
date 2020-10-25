package gary.springframework.bulletin.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class VerificationToken extends BaseEntity {

    /*時效設定為一天*/
    private final int EXPIRATION = 60 * 24 ;

    private String token;

    private Date expiryDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id" , foreignKey = @ForeignKey(name = "FK_VERIFY_USER"))
    private User user;

    public VerificationToken() {
        super();
    }

    public VerificationToken(final String token) {
        super();

        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public VerificationToken(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

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
