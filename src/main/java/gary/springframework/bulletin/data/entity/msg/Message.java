package gary.springframework.bulletin.data.entity.msg;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * 紀錄每個人發言用
 */
@Entity
@Table(name = "MESSAGE")
@Data
public class Message implements Serializable {

    /** 訊息ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private int messageID;

    /** 訊息擁有者ID */
    @Column(name = "USER_ID")
    private int userID;

    /** 訊息內容 */
    @Column(name = "MESSAGE_CONTENT")
    private String messageContent;

    /** 訊息日期 */
    @Column(name = "MESSAGE_DATE")
    private Date messageDate;

}
