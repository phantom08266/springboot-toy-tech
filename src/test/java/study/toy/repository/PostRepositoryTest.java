package study.toy.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.toy.domain.Post;

@DataJpaTest
class PostRepositoryTest {

    private final PostRepository postRepository;

    public PostRepositoryTest(@Autowired PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Test
    void 게시물_저장에_성공한다() {
        Post post = Post.of("title", "content of post", "#hash1");
        Post savedPost = postRepository.save(post);

        assertThat(savedPost.getId()).isNotNull();
    }
}