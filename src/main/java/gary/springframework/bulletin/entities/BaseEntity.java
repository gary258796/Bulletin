package gary.springframework.bulletin.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * BaseEntity 放共同會使用之資料庫欄位 Ex: Id
 * 讓其他Entity去擴展, 並且本身不會被實體化成一張表格
 */

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
