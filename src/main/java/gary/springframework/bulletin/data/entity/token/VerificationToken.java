package gary.springframework.bulletin.data.entity.token;

import gary.springframework.bulletin.data.entity.User;
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
@Table(name = "VERIFICATION_TOKEN")
public class VerificationToken extends TokenBase implements Serializable {

    public VerificationToken(final String token, final User user) {
        super();
        this.token = token;
        this.userID = user.getId();
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

}
