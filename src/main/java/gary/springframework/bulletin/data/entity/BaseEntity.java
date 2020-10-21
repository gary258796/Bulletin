package gary.springframework.bulletin.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * BaseEntity 放共同會使用之資料庫欄位 Ex: Id
 * 讓其他Entity去擴展
 * 因為 @MappedSuperclass 註解的關西所以本身不會被實體化成一張表格
 * 但是繼承的class如果標上Entity還是會把包含的欄位一起生成表格
 */

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
