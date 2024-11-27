package miniproject.web02.converter;

import miniproject.web02.domain.Review;
import miniproject.web02.web.dto.ReviewSearchDTO.ReviewSearchResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewSearchConverter {

    public static ReviewSearchResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){
        return ReviewSearchResponseDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getReviewId())
                .lectureName(review.getLecture().getName())
                .platform(String.valueOf(review.getLecture().getPlatform()))
                .teacher(review.getLecture().getTeacher())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }

    public static ReviewSearchResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewList){

        List<ReviewSearchResponseDTO.ReviewPreviewDTO> missionPreviewDTOList = reviewList.stream()
                .map(ReviewSearchConverter::toReviewPreviewDTO).collect(Collectors.toList());

        return ReviewSearchResponseDTO.ReviewPreviewListDTO.builder()
                .reviewList(missionPreviewDTOList)
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .build();
    }
}
