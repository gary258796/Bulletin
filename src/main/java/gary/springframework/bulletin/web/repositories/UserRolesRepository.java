package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.Role;
import gary.springframework.bulletin.data.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Mapper
@Repository
public interface UserRolesRepository {

    /**
     * Store all the roles to @user
     * @param user :
     * @param roles : roles user owned
     */
    void storeUserRoles(@Param("user") User user,
                        @Param("roles") Collection<Role> roles);

    /**
     * Get roles of user with userId == @id
     * @param userId:
     * @return Role of user , whose id == @userId
     */
    Collection<Role> getRolesByUserId(@Param("userId") int userId);

}
