package gary.springframework.bulletin.security;

import gary.springframework.bulletin.entities.Role;
import gary.springframework.bulletin.entities.User;
import gary.springframework.bulletin.web.repositories.UserRepository;
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

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * functionName不可改變, 但我們可以在內容決定要以哪個欄位作為驗證
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

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword().toLowerCase(),
                true, true, true, true,
                getAuthorities(user.getRoles()) );
    }

    // methods
    private static List<GrantedAuthority> getAuthorities (final Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
