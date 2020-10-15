package gary.springframework.bulletin.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

/**
 * 註冊時,傳回Browser的ResponseBody
 */
@Data
@ToString
@JsonInclude
public class RegistResponse implements Serializable {

    /**
     * @Return String : "successful" , "fail"
     */
    private String returnStatus;

    /**
     * @Return 錯誤msg
     */
    private String returnMsg;

}
