package miniproject.web02.web.dto.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniproject.web02.domain.enums.StudyTime;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private BigDecimal rating; //별점
    private String content; //강의평
    private StudyTime studyTime; //강의 수강 기간(enum)
    private Long lectureId; //강의 ID
}
