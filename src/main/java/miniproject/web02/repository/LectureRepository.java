package miniproject.web02.repository;

import miniproject.web02.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
    Optional<Lecture> findByName(String name);
}
