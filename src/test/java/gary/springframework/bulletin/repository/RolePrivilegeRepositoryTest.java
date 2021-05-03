package gary.springframework.bulletin.repository;

import gary.springframework.bulletin.data.entity.Privilege;
import gary.springframework.bulletin.data.entity.Role;
import gary.springframework.bulletin.web.repositories.RolePrivilegeRepository;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RolePrivilegeRepositoryTest {

    @Autowired
    private RolePrivilegeRepository rolePrivilegeRepository;

    @Test
    public void getPrivilegeOfRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(0, "ADMIN"));
        roles.add(new Role(1, "USER"));

        List<Privilege> privileges = rolePrivilegeRepository.getPrivilegeOfRoles(roles);
        assert privileges.size() == 0;
    }

    @Test
    public void savePrivilegesOfRole() {
        Role role = new Role();
        role.setRoleID(0);
        role.setRoleName("ADMIN");

        Privilege privilege = new Privilege();
        privilege.setPrivilegeID(3);
        privilege.setPrivilegeName("ADD");
        Privilege privilege1 = new Privilege();
        privilege1.setPrivilegeID(4);
        privilege1.setPrivilegeName("DELETE");
        List<Privilege> privileges = new ArrayList<>(Arrays.asList(privilege, privilege1));

        rolePrivilegeRepository.savePrivilegesOfRole(role, privileges);

        assert rolePrivilegeRepository.getPrivilegeOfRoles(Collections.singletonList(role)).size() == 2;
    }

}
