package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.model.dto.UserLoginDto;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get 顯示登入頁面用
     * @return
     */
    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "login/login" ;
    }

    /**
     * Post 登入動作
     * @return
     */
    @PostMapping(value = "/abc")
    public @ResponseBody String formLogin(@RequestBody UserLoginDto userLoginDto, HttpSession session) {

        User user = userService.findByUserName(userLoginDto.getUserName());

        // check DB if this user is exist and with correct password
        if( user != null && user.getPassword().equals(userLoginDto.getPassword()) ){
            session.setAttribute("User", user );
            return "successful";
        }
        else
            return "fail";

    }

}
