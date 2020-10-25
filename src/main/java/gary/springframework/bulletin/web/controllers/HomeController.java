package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 登入後 go home page
     * @param user, @AuthenticationPrincipal 會自動由Spring回傳認證過的Authentication.getPrinciple()
     *              所以型別要對,不然會直接回傳 Null
     * @return
     */
    @GetMapping(value = "/home")
    public String showHomePage(@AuthenticationPrincipal User user) {

        return "home/home" ;
    }

}
