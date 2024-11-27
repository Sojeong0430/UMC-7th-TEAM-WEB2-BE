package miniproject.web02.web.dto.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniproject.web02.domain.enums.StudyTime;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {
    // 개별 리뷰 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDTO {
        Long reviewId;
        BigDecimal rating;
        String content;
        StudyTime studyTime;
        Integer likes;
        LocalDateTime createdAt;
        String imageUrl;
        Long lectureId;
    }

    // 리뷰 목록 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListDTO {
        List<ReviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
    // 리뷰 이미지 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewImageDTO {
        Long reviewImageId;
        String imageUrl;
        LocalDateTime createdAt;
    }
    // 리뷰 이미지 목록 정보
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewImageListDTO {
        List<ReviewImageDTO> reviewImageList;
    }
}
