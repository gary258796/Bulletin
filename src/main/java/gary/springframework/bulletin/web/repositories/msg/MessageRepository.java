package gary.springframework.bulletin.web.repositories.msg;

import gary.springframework.bulletin.data.entity.msg.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
