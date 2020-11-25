package gary.springframework.bulletin.data.entity.msg;

import gary.springframework.bulletin.data.entity.BaseEntity;
import gary.springframework.bulletin.data.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * 紀錄每則訊息的留言內容
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "comments")
public class Comment extends BaseEntity {

    /** 多個留言會對應到一個發言*/
    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    /** 一個留言 會對應一個User */
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
    private User user;

    /** 留言內容 */
    private String content;

    /** 留言日期 */
    private Date date;

    /** 留言時間 */
    private Time time;
}
