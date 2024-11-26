package miniproject.web02.repository;

import miniproject.web02.domain.Review;
import miniproject.web02.domain.enums.Category;
import miniproject.web02.domain.enums.Level;
import miniproject.web02.domain.enums.StudyTime;
import org.springframework.data.jpa.domain.Specification;

public class ReviewSpecification {

    public static Specification<Review> hasCategory(Category category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lecture").get("category"), category);
    }

    public static Specification<Review> hasLevel(Level level) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("lecture").get("level"), level);
    }

    public static Specification<Review> hasStudyTime(StudyTime studyTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("studyTime"), studyTime);
    }
}
