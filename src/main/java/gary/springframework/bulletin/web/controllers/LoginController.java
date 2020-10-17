package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.entities.User;
import gary.springframework.bulletin.models.dto.UserLoginDto;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/login")
    public @ResponseBody String formLogin(@RequestBody UserLoginDto userLoginDto, HttpSession session) {

        User user = userService.findByAccount( userLoginDto.getAccount() );

        // check DB if this user is exist and with correct password
        if( user != null && user.getPassword().equals(userLoginDto.getPassword()) ){
            session.setAttribute("User", user );
            return "successful";
        }
        else
            return "fail";

    }

}
