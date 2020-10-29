package gary.springframework.bulletin.data.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailFieldPojo {

    private String toAddress;
    private String subject;
    private String text;
    private String fromAddress;

}
