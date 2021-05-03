package gary.springframework.bulletin.repository;

import gary.springframework.bulletin.data.entity.msg.Comment;
import gary.springframework.bulletin.web.repositories.msg.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.List;

@DataJpaTest
@EnableJpaRepositories(basePackageClasses = CommentRepository.class)
@EntityScan(basePackageClasses = Comment.class)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void saveComment() {
        Comment comment = new Comment();
        comment.setUserID(1);
        comment.setCommentID(1);
        comment.setMessageID(1);
        comment.setCommentContent("Test");
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        comment.setCommentDate(sqlDate);

        commentRepository.save(comment);
        List<Comment> comments = commentRepository.findAll();
        for(Comment c : comments) {
            System.out.println(c.getCommentID());
            System.out.println(c.getCommentContent());
        }
    }

}
