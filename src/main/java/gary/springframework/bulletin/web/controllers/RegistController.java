package gary.springframework.bulletin.web.controllers;

import gary.springframework.bulletin.entities.User;
import gary.springframework.bulletin.models.dto.UserRegistDto;
import gary.springframework.bulletin.models.response.RegistResponse;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistController {

    private final UserService userService;

    public RegistController(UserService userService) {
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
     * @return
     */
    @PostMapping(value = "/regist")
    public @ResponseBody
    RegistResponse doRegisteration(@RequestBody UserRegistDto userRegistDto) {

        RegistResponse registResponse = new RegistResponse();

        // check if already exists in DB
        User theUser = userService.findByAccountAndEmail(userRegistDto.getAccount(), userRegistDto.getEmail()) ;

        // TRUE : refuse register action
        if( theUser != null ){
            registResponse.setReturnStatus("fail");
            registResponse.setReturnMsg("Email and account are both used already! Please type again.");
        } else { // FALSE : Save data to DB

            User user = new User( userRegistDto.getAccount() , userRegistDto.getEmail() , userRegistDto.getPassword() );

            userService.save(user);

            registResponse.setReturnStatus("successful");

        }

        return registResponse;
    }
}
