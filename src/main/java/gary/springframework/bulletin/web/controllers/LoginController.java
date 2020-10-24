package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.web.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get 顯示登入頁面用
     * @return
     * 參數可加入Model model, CsrfToken token
     * the token will be injected automatically
     */
    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login/login" ;
    }


    /**
     * Post登入驗證動作, 交由Spring Security去處理
     * @return
     */


}
