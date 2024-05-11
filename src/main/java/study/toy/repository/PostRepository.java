package study.toy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.toy.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
}
