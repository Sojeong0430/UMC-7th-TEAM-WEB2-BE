package miniproject.web02.service.ReviewSearch;

import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import org.springframework.data.domain.Page;

public interface ReviewSearchCommandService {
    Page<Review> getReviewList (String Keyword,Integer page);
}
