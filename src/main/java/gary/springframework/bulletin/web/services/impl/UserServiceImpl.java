package gary.springframework.bulletin.web.services.impl;

import gary.springframework.bulletin.entities.User;
import gary.springframework.bulletin.web.repositories.UserRepository;
import gary.springframework.bulletin.web.services.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** -----------------------------------------------------------------  */

    @Override
    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByAccountAndEmail(String account, String email) {
        return userRepository.findByAccountAndEmail(account, email);
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
