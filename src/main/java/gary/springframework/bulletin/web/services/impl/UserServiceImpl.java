package gary.springframework.bulletin.web.services.impl;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.token.ResetPasswordToken;
import gary.springframework.bulletin.data.entity.token.VerificationToken;
import gary.springframework.bulletin.data.model.dto.UserRegistDto;
import gary.springframework.bulletin.normalstuff.exception.UserAlreadyExistException;
import gary.springframework.bulletin.web.repositories.*;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository;
    private final UserRolesRepository userRolesRepository;
    private final VerificationTokenRepository tokenRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, UserRolesRepository userRolesRepository, VerificationTokenRepository tokenRepository, ResetPasswordTokenRepository resetPasswordTokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRolesRepository = userRolesRepository;
        this.tokenRepository = tokenRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    /** ---------------------------Custom Methods--------------------------------------  */

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public User findByUserNameAndEmail(String userName, String email) {
        return userRepository.findByUserNameAndUserEmail(userName, email);
    }

    @Override
    public User findUserByToken(String token) {

        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if( verificationToken != null ) {
            Optional<User> user = userRepository.findById(verificationToken.getId());
            return user.orElse(null);
        }

        return null;
    }

    /**
     * 註冊新用戶, 如果帳號或者信箱有任一是已被使用就拒絕註冊
     * @param @Notnull userRegistDto
     * @return
     * @throws UserAlreadyExistException
     */
    @Override
    public User registerNewUserAccount(UserRegistDto userRegistDto) throws UserAlreadyExistException {

        if( emailExist(userRegistDto.getEmail()) && userNameExist(userRegistDto.getUserName()) )
            throw new UserAlreadyExistException("This Username and Email are used already");
        else if( emailExist(userRegistDto.getEmail()) && !userNameExist(userRegistDto.getUserName()) )
            throw new UserAlreadyExistException("This Email is used already");
        else if( !emailExist(userRegistDto.getEmail()) && userNameExist(userRegistDto.getUserName()) )
            throw new UserAlreadyExistException("This Username is used already");

        // no problem , register user
        User user = new User();
        user.setUserEmail( userRegistDto.getEmail() );
        user.setUserName( userRegistDto.getUserName() );
        user.setUserPassword( passwordEncoder.encode(userRegistDto.getPassword()) );
        save(user);
        userRolesRepository.storeUserRoles(user, Collections.singletonList(roleRepository.findByRoleName("ROLE_USER")));

        return user;
    }

    @Override
    public User getUserByResetPasswordToken(String resetToken) {

        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(resetToken);

        Optional<User> user = userRepository.findById(resetPasswordToken.getUserID());

        return user.orElse(null);
    }

    @Override
    public void changeUserPassword(User user, String newPassword) {
        user.setUserPassword( passwordEncoder.encode(newPassword) );
        userRepository.save(user);
    }

    @Override
    public Boolean emailExist(String email) {
        return findByEmail(email) != null;
    }

    @Override
    public Boolean userNameExist(String userName) {
        return findByUserName(userName) != null;
    }

    /** -----------------------------------------------------------------  */

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
