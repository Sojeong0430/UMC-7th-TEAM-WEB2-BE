package miniproject.web02.domain;

import jakarta.persistence.*;
import lombok.*;
import miniproject.web02.domain.common.BaseEntity;
import miniproject.web02.domain.enums.StudyTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(nullable = false, length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    private StudyTime studyTime;

    private Integer likes;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lectureId")
    private Lecture lecture;
}
