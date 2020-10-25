package gary.springframework.bulletin.web.services;

import gary.springframework.bulletin.data.entity.VerificationToken;
import gary.springframework.bulletin.normalstuff.exception.UserAlreadyExistException;
import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.model.dto.UserRegistDto;

public interface UserService extends CrudService<User, Long>{

    User findByUserName(String userName);

    User findByEmail(String email);

    User findByUserNameAndEmail(String userName, String email);


    // Custom
    User registerNewUserAccount(UserRegistDto userRegistDto) throws UserAlreadyExistException;

    Boolean emailExist(String email);

    Boolean userNameExist(String userName);

    // Token related
    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByToken(String token);

}
