package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    Privilege findByPrivilegeName(String name);

    @Override
    void delete(Privilege privilege);

}
