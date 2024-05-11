package study.toy.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import study.toy.config.JpaConfig;
import study.toy.domain.Post;

@Import(JpaConfig.class)
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

    @Test
    void 등록된_게시물은_저자가_admin이다() {
        // TODO [JH] : 일단은 무조건 admin이고 나중에 시큐리티 붙이면 수정해야함
        Post post = Post.of("title", "content of post", "#hash1");
        Post savedPost = postRepository.save(post);

        assertThat(savedPost.getCreatedBy()).isEqualTo("admin");
    }
}