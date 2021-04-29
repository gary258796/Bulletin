package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.msg.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
