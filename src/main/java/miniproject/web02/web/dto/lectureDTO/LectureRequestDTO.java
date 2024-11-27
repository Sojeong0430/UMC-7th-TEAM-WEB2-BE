package miniproject.web02.web.dto.lectureDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniproject.web02.domain.enums.Category;
import miniproject.web02.domain.enums.Level;
import miniproject.web02.domain.enums.Platform;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureRequestDTO {

    @NotBlank(message = "강의 이름 입력이 필요합니다.")
    private String name;

    @NotBlank(message = "강사 이름 입력이 필요합니다.")
    private String teacher;

    @NotNull(message = "플랫폼 정보는 필수 입력 값입니다.")
    private Platform platform;

    @NotNull(message = "카테고리는 필수 입력 값입니다.")
    private Category category;

    @NotNull(message = "레벨은 필수 입력 값입니다.")
    private Level level;

    //private MultipartFile image;
}
