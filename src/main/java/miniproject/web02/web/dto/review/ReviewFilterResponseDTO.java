package miniproject.web02.web.dto.review;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import miniproject.web02.domain.enums.Category;
import miniproject.web02.domain.enums.Platform;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewFilterResponseDTO {
    private Long reviewId;
    private String lectureName;
    private Platform platform;
    private String lectureTeacher;
    private String content;
    private BigDecimal rating;
    private LocalDateTime createdAt;
    private Category lectureCategory;
    private Integer likes;
    private List<String> imageUrls;
}