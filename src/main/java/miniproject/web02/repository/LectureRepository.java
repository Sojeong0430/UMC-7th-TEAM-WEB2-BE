package miniproject.web02.repository;

import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {
}
