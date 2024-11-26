package miniproject.web02.service.reviewService;

import miniproject.web02.web.dto.reviewDTO.ReviewResponseDTO;

public interface ReviewService {
    ReviewResponseDTO.ReviewListDTO getLectureReviews(long lectureId, Integer rating, String sortField, Integer page);
}
