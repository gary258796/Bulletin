package gary.springframework.bulletin.security;

import gary.springframework.bulletin.data.entity.Privilege;
import gary.springframework.bulletin.data.entity.Role;
import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.web.repositories.RolePrivilegeRepository;
import gary.springframework.bulletin.web.repositories.UserRepository;
import gary.springframework.bulletin.web.repositories.UserRolesRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final RolePrivilegeRepository rolePrivilegeRepository;
    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;

    public MyUserDetailsService(RolePrivilegeRepository rolePrivilegeRepository, UserRolesRepository userRolesRepository, UserRepository userRepository) {
        this.rolePrivilegeRepository = rolePrivilegeRepository;
        this.userRolesRepository = userRolesRepository;
        this.userRepository = userRepository;
    }

    /**
     * functionName不可改變, 但我們可以在內容決定要以哪個欄位作為Spring Security的驗證
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if( user == null ) {
            throw new UsernameNotFoundException("No user found with userName: " + username );
        }

        return new org.springframework.security.core.userdetails.User(user.getUserCode(), user.getUserPassword(),
                user.getUserEnabled(), true, true, true,
                getAuthorities(userRolesRepository.getRolesByUserId(user.getId())));
    }

    // methods
    /**
     * 取得該登入者所擁有之所有授權
     * @param roles
     * @return
     */
    private List<GrantedAuthority> getAuthorities (final Collection<Role> roles) {
        return getGrantedAuthorities( getPrivileges(roles) );
    }

    /**
     * 取得該使用者所有所屬角色(可能不只一個角色)的所有權限
     * @param roles
     * @return
     */
    private List<String> getPrivileges(final Collection<Role> roles ) {
        List<Privilege> privileges = rolePrivilegeRepository.getPrivilegeOfRoles(roles);

        return privileges.stream()
                         .map(Privilege::getPrivilegeName)
                         .collect(Collectors.toList());
    }

    /**
     * 依照權限(privilege)去產生授權
     * @param privileges: 權限
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
