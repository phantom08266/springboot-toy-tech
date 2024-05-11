package study.toy.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@ToString
@EntityListeners(AuditingEntityListener.class)
@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(length = 100)
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(length = 100)
    private String updatedBy;
}
