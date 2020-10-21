package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.model.dto.UserRegistDto;
import gary.springframework.bulletin.data.model.response.GenericResponse;
import gary.springframework.bulletin.normalstuff.event.OnRegistrationCompleteEvent;
import gary.springframework.bulletin.normalstuff.exception.UserAlreadyExistException;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class RegistController {

    private final UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public RegistController(UserService userService) { super();
        this.userService = userService;
    }

    /**
     * 進入到註冊頁面用
     * @return
     */
    @GetMapping(value = "/regist")
    public String showRegisterPage() {
        return "regist/regist";
    }

    /**
     * 註冊流程
     * 通不過Java校驗api的欄位會透過HandleValidationExceptions class裡面去抓取所有controller丟出但沒去處理之exceptions
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/regist")
    public GenericResponse doRegisteration(@Valid @RequestBody final UserRegistDto userRegistDto,
                                           final HttpServletRequest request, final Errors errors) {

        GenericResponse genericResponse = new GenericResponse("successful");

        try {
            final User registeredUser = userService.registerNewUserAccount(userRegistDto);
            String appUrl = getAppUrl(request);
            Locale locale = LocaleContextHolder.getLocale();

            // 發佈事件
            applicationEventPublisher.publishEvent( new OnRegistrationCompleteEvent(registeredUser, appUrl, locale) );

        } catch ( UserAlreadyExistException userAlreadyExistException){
            genericResponse = new GenericResponse("fail", userAlreadyExistException.getMessage() );
        }

        return genericResponse;
    }

    /* Methods Below */

    /**
     * 取得路徑
     * @param request
     * @return
     */
    private String getAppUrl( HttpServletRequest request ) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
