package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.Privilege;
import gary.springframework.bulletin.data.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface RolePrivilegeRepository {

    List<Privilege> getPrivilegeOfRoles(@Param(value = "roles") Collection<Role> roles);

    void savePrivilegesOfRole(@Param(value = "role") Role role, @Param(value = "privileges") Collection<Privilege> privileges);

}
