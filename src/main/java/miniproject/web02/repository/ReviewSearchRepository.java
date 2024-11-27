package miniproject.web02.repository;

import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewSearchRepository extends JpaRepository<Review,Long> {
    Page<Review> findAllByLecture(Lecture lecture, PageRequest pageRequest);
    List<Review> findAllByLecture(Lecture lecture);
}
