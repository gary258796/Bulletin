package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.Role;
import gary.springframework.bulletin.data.entity.User;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public interface UserRolesRepository {

    void storeUserRoles(User user, Collection<Role> roles);

    Collection<Role> getRolesByUserId(int userId);

}
