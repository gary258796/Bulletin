package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.entities.User;
import gary.springframework.bulletin.models.UserReq;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String login() {
        return "login/login" ;
    }

    /**
     * Post 登入動作
     * @return
     */
    @PostMapping(value = "/login")
    public @ResponseBody String formLogin(@RequestBody UserReq userReq) {

        // go and check DB if this user is exist and with correct password
        User user = userService.findByAccount( userReq.getAccount() );

        if( user != null ) {
            System.out.println("userReq: " + userReq.getPassword());
            System.out.println("user: " + user.getPassword());
            if( user.getPassword().equals(userReq.getPassword()) ){
                System.out.println("Equal!");
            }
        }
        else {
            System.out.println("No User found");
        }

        return "Test" ;
    }

    // Methods
}
