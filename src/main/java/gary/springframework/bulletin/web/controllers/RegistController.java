package gary.springframework.bulletin.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistController {

    @GetMapping(value = "/regist")
    public String register() {
        return "regist/regist";
    }
}
