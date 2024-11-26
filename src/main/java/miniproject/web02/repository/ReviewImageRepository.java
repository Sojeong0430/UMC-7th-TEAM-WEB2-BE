package miniproject.web02.repository;

import miniproject.web02.domain.ReviewImage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<ReviewImage> findTop3ByReview_Lecture_LectureIDOrderByCreatedAtDesc(Long lectureId);
}
