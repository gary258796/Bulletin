package gary.springframework.bulletin.web.services.impl;

import gary.springframework.bulletin.exception.UserAlreadyExistException;
import gary.springframework.bulletin.entities.User;
import gary.springframework.bulletin.models.dto.UserRegistDto;
import gary.springframework.bulletin.web.repositories.RoleRepository;
import gary.springframework.bulletin.web.repositories.UserRepository;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /** -----------------------------------------------------------------  */

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUserNameAndEmail(String userName, String email) {
        return userRepository.findByUserNameAndEmail(userName, email);
    }

    /**
     * 註冊新用戶, 如果帳號或者信箱有任一是已被使用就拒絕註冊
     * @param @Notnull userRegistDto
     * @return
     * @throws UserAlreadyExistException
     */
    @Override
    @Transactional
    public User registerNewUserAccount(UserRegistDto userRegistDto) throws UserAlreadyExistException {

        if( emailExist(userRegistDto.getEmail()) && userNameExist(userRegistDto.getUserName()) )
            throw new UserAlreadyExistException("This Username and Email are used already");
        else if( emailExist(userRegistDto.getEmail()) && !userNameExist(userRegistDto.getUserName()) )
            throw new UserAlreadyExistException("This Email is used already");
        else if( !emailExist(userRegistDto.getEmail()) && userNameExist(userRegistDto.getUserName()) )
            throw new UserAlreadyExistException("This Username is used already");

        // no problem , register user
        User user = new User();
        user.setEmail( userRegistDto.getEmail() );
        user.setUserName( userRegistDto.getUserName() );
        user.setPassword( userRegistDto.getPassword() );
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        return save(user);
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
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
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
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
