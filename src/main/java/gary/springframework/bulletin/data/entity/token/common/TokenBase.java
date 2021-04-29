package gary.springframework.bulletin.data.entity.token.common;

import java.util.Calendar;
import java.util.Date;

public class TokenBase {

    /** 過期期限 - 兩小時 */
    protected int EXPIRATION = 60 * 2 ;

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

}
