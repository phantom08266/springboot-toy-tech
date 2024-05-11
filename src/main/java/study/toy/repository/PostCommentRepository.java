package study.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.toy.domain.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
