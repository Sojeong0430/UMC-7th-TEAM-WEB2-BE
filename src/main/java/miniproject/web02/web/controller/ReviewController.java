package miniproject.web02.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import miniproject.web02.domain.enums.Category;
import miniproject.web02.domain.enums.Level;
import miniproject.web02.domain.enums.StudyTime;
import miniproject.web02.service.ReviewFilterService;
import miniproject.web02.web.dto.review.FilterRequestDTO;
import miniproject.web02.web.dto.review.ReviewFilterResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewFilterService reviewFilterService;

    @GetMapping
    public ResponseEntity<List<ReviewFilterResponseDTO>> getFilteredReviews(
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "level", required = false) Level level,
            @RequestParam(value = "studyTime", required = false) StudyTime studyTime,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        FilterRequestDTO filterRequestDTO = new FilterRequestDTO(category, level, studyTime, sort);
        List<ReviewFilterResponseDTO> response = reviewFilterService.filterAndSortReviews(filterRequestDTO);
        return ResponseEntity.ok(response);
    }
}
