package miniproject.web02.service.rating;

import miniproject.web02.domain.Lecture;

import java.math.BigDecimal;
import java.util.List;

public interface RatingCommandService {
    public int[] getRatingCounts(List<BigDecimal> lectureReviewRatingList);
    public List<BigDecimal> getRatingList (Lecture lecture);
}
