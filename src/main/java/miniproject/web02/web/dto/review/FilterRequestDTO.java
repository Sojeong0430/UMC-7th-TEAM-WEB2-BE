package miniproject.web02.web.dto.review;

import lombok.*;
import miniproject.web02.domain.enums.Category;
import miniproject.web02.domain.enums.Level;
import miniproject.web02.domain.enums.StudyTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequestDTO {
    private Category category;
    private Level level;
    private StudyTime studyTime;
    private String sort; // "recommended" or "latest"
}
