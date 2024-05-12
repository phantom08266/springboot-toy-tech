package study.toy.repository;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import study.toy.config.JpaConfig;
import study.toy.domain.Post;
import study.toy.domain.PostComment;

@Import(JpaConfig.class)
@DataJpaTest
class PostRepositoryTest {
    @Autowired
    private EntityManager em;
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    public PostRepositoryTest(
                              @Autowired PostRepository postRepository,
                              @Autowired PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
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

    @Test
    @Transactional
    void 댓글_순서가_내림차순이다() {
        Post post = Post.of("title", "content of post", "#hash1");
        postRepository.save(post);

        PostComment comment1 = PostComment.of("comment1", post);
        PostComment comment2 = PostComment.of("comment2", post);
        PostComment comment3 = PostComment.of("comment3", post);

        postCommentRepository.save(comment1);
        postCommentRepository.save(comment2);
        postCommentRepository.save(comment3);


        // flush를 해야 영속성 컨텍스트에 있는 데이터를 DB에 반영한다.
        em.flush();
        // 영속성을 비워줘야 다시 DB를 조회한 다음 이를 영속성 컨텍스트에 담기때문
        em.clear();

        Post findPost = postRepository.findById(post.getId()).orElse(null);

        Set<PostComment> comments = findPost.getComments();

        assertThat(comments).hasSize(3);
        assertThat(comments).containsExactly(comment3, comment2, comment1);
    }
}