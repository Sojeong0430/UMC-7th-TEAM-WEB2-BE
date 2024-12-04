package miniproject.web02.repository;

import miniproject.web02.domain.Review;
import org.springframework.data.jpa.domain.Specification;

public class LectureReviewSpecification {

    public static Specification<Review> hasRating(Integer rating) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("rating"), rating);
    }

}
