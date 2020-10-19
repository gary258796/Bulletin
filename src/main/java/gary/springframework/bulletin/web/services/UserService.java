package gary.springframework.bulletin.web.services;

import gary.springframework.bulletin.exception.UserAlreadyExistException;
import gary.springframework.bulletin.entities.User;
import gary.springframework.bulletin.models.dto.UserRegistDto;

public interface UserService extends CrudService<User, Long>{

    User findByAccount(String account);

    User findByEmail(String email);

    User findByAccountAndEmail(String account, String email);



    // Custom
    User registerNewUserAccount(UserRegistDto userRegistDto) throws UserAlreadyExistException;

    Boolean emailExist(String email);

    Boolean accountExist(String account);
}
