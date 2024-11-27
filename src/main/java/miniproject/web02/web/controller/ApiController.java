package miniproject.web02.web.controller;

import lombok.RequiredArgsConstructor;
import miniproject.web02.converter.ReviewSearchConverter;
import miniproject.web02.converter.TotalRatingConverter;
import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import miniproject.web02.repository.LectureRepository;
import miniproject.web02.service.ReviewSearch.ReviewSearchCommandService;
import miniproject.web02.service.rating.RatingCommandService;
import miniproject.web02.web.dto.ReviewSearchDTO.ReviewSearchResponseDTO;
import miniproject.web02.web.dto.totalRatingDTO.totalRatingResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class ApiController {

    private final LectureRepository lectureRepository;
    private final RatingCommandService ratingCommandService;
    private final ReviewSearchCommandService reviewSearchCommandService;

    @GetMapping("/rating_info/{lectureId}")
    public totalRatingResponseDTO.getRatingInfoDTO RatingInfo (@PathVariable(name = "lectureId") Long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId).get();
        List<BigDecimal> lectureReviewRatingList = ratingCommandService.getRatingList(lecture);
        if (lectureReviewRatingList.isEmpty()) {
            int[] ratingCounts = {0,0,0,0,0};
            return (TotalRatingConverter.toTotalRatigDTO(lecture,ratingCounts));
        }
        int[] ratingCounts = ratingCommandService.getRatingCounts(lectureReviewRatingList);
        return(TotalRatingConverter.toTotalRatigDTO(lecture,ratingCounts,lectureReviewRatingList));

    } //해당 강의의 별점 평균, 각 점수대별 리뷰 수 조회
    @GetMapping("/search")
    public ReviewSearchResponseDTO.ReviewPreviewListDTO searchReviewByLectureName(@RequestParam("keyword") String keyword, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Page<Review> reviewList = reviewSearchCommandService.getReviewList(keyword,page);
        return (ReviewSearchConverter.toReviewPreviewListDTO(reviewList));
    } //강의명으로 리뷰들 검색
}
