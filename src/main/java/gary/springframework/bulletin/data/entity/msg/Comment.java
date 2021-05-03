package gary.springframework.bulletin.data.entity.msg;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * 紀錄每則訊息的留言內容
 */
@Entity
@Table(name = "COMMENT")
@Data
public class Comment implements Serializable {

    /** 留言之ID */
    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    /** 留言所屬之訊息ID */
    @Column(name = "MESSAGE_ID")
    private int messageID;

    /** 留言所屬留言者ID */
    @Column(name = "USER_ID")
    private int userID;

    /** 留言內容 */
    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    /** 留言時間 */
    @Column(name = "COMMENT_DATE")
    private Date commentDate;

}
