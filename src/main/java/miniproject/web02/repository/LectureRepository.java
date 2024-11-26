package miniproject.web02.repository;

import miniproject.web02.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
    Lecture findByName(String name);
}
