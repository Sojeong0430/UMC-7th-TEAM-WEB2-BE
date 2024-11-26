package miniproject.web02.repository;

import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewRepository extends JpaRepository<Review,Long>, JpaSpecificationExecutor<Review> {
    List<Review> findAllByLecture(Lecture lecture);
}
