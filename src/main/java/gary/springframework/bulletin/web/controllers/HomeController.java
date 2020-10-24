package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.data.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    /**
     * 登入後 go home page
     * @param request
     * @return
     */
    @GetMapping(value = "/home")
    public String showHomePage(HttpServletRequest request) {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        return "home/home" ;
    }

}
