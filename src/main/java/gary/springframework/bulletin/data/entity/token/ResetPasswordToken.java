package gary.springframework.bulletin.data.entity.token;

import gary.springframework.bulletin.data.entity.token.common.TokenBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "RESET_PASSWORD_TOKEN")
public class ResetPasswordToken extends TokenBase implements Serializable {

    public ResetPasswordToken(String token, int userID) {
        this.token = token;
        this.userID = userID;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

}
