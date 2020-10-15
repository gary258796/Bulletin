package gary.springframework.bulletin.web.services;

import gary.springframework.bulletin.entities.User;

public interface UserService extends CrudService<User, Long>{

    User findByAccount(String account);

    User findByEmail(String email);

    User findByAccountAndEmail(String account, String email);

}
