package miniproject.web02.web.dto.ReviewSearchDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReviewSearchResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewDTO{
        Long reviewId;
        String lectureName;
        String platform;
        String teacher;
        BigDecimal rating;
        String content;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreviewListDTO{
        List<ReviewPreviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

}
