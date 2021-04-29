package gary.springframework.bulletin.data.entity.token.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

// * TokenBase 放token共同會使用之資料庫欄位 Ex: token, expiryDate
// * 讓其他token related Entity去擴展
// * 因為 @MappedSuperclass 註解的關西所以本身不會被實體化成一張表格
// * 但是繼承的class如果標上Entity還是會把包含的欄位一起生成表格
// */
@MappedSuperclass
public class TokenBase implements Serializable {

    /** 過期期限 - 兩小時 */
    protected int EXPIRATION = 60 * 2 ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected int id;

    @Column(name = "USER_ID")
    protected int userID;

    @Column(name = "TOKEN")
    protected String token;

    @Column(name = "EXPIRY_DATE")
    protected Date expiryDate;

    /**
     * Return Expiry date time
     * current time + EXPIRATION = expiry time
     * @param expiryTimeInMinutes :
     * @return :
     */
    protected Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

}
