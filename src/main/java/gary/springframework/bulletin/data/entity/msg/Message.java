package gary.springframework.bulletin.data.entity.msg;

import gary.springframework.bulletin.data.entity.BaseEntity;
import gary.springframework.bulletin.data.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

/**
 * 紀錄每個人發言用
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "messages")
public class Message extends BaseEntity {

    /** 一個發言 會對應一個User */
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(name = "FK_MESSAGE_USER"))
    private User user;

    /** 發言內容 */
    private String content;

    /** 發言日期 */
    private Date date;

    /** 發言時間 */
    private Time time;

    /** 所有留言 */
    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    private Set<Comment> comments;


}
