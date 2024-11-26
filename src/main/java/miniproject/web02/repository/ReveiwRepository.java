package miniproject.web02.repository;

import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReveiwRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByLecture(Lecture lecture);
}
