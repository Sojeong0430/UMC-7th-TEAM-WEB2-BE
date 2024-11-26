package miniproject.web02.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import miniproject.web02.apiPayLoad.ApiResponse;
import miniproject.web02.apiPayLoad.code.status.SuccessStatus;
import miniproject.web02.converter.TotalRatingConverter;
import miniproject.web02.domain.Lecture;
import miniproject.web02.repository.LectureRepository;
import miniproject.web02.service.lectureSerivce.LectureService;
import miniproject.web02.service.rating.RatingCommandService;
import miniproject.web02.web.dto.lectureDTO.LectureResponseDTO;
import miniproject.web02.web.dto.reviewDTO.ReviewResponseDTO;
import miniproject.web02.web.dto.totalRatingDTO.totalRatingResponseDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class ApiController {

    private final LectureRepository lectureRepository;
    private final LectureService lectureService;
    private final RatingCommandService ratingCommandService;

    @GetMapping("/rating_info/{lectureId}")
    public totalRatingResponseDTO.getRatingInfoDTO RatingInfo (@PathVariable(name = "lectureId") Long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId).get();
        List<BigDecimal> lectureReviewRatingList = ratingCommandService.getRatingList(lecture);
        int[] ratingCounts = ratingCommandService.getRatingCounts(lectureReviewRatingList);
        return(TotalRatingConverter.toTotalRatigDTO(lecture,ratingCounts,lectureReviewRatingList));
    } //해당 강의의 별점 평균, 각 점수대별 리뷰 수 조회

    @Operation(summary = "강의 정보 조회 API", description = "강의 상세 페이지의 강의 정보 조회")
    @GetMapping("/lecture_info/{lectureId}")
    public ApiResponse<LectureResponseDTO.LectureDTO> getLectureInfo (@PathVariable(name = "lectureId") Long lectureId){
        LectureResponseDTO.LectureDTO lectureDTO = lectureService.getLecture(lectureId);
        return ApiResponse.of(SuccessStatus.SUCCESS_FETCH_LECTURE, lectureDTO);
    }
}
