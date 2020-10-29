package gary.springframework.bulletin.web.services;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.model.dto.UserRegistDto;
import gary.springframework.bulletin.normalstuff.exception.UserAlreadyExistException;

public interface UserService extends CrudService<User, Long>{

    User findByUserName(String userName);

    User findByEmail(String email);

    User findByUserNameAndEmail(String userName, String email);

    // Custom
    User findUserByToken(String token);

    User registerNewUserAccount(UserRegistDto userRegistDto) throws UserAlreadyExistException;

    User getUserByResetPasswordToken(String resetToken);

    void changeUserPassword(User user, String newPassword);

    Boolean emailExist(String email);

    Boolean userNameExist(String userName);

}
