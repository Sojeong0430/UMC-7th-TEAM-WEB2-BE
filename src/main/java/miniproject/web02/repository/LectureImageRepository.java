package miniproject.web02.repository;

import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.LectureImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureImageRepository extends JpaRepository<LectureImage,Long> {
    Optional<LectureImage> findFirstByLecture(Lecture lecture);
}
