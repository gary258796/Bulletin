package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.Privilege;
import gary.springframework.bulletin.data.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RolePrivilegeRepository {

    List<Privilege> getPrivilegeOfRoles(Collection<Role> roles);

}
