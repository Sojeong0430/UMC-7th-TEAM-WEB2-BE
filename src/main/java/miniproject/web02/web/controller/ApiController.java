package miniproject.web02.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import miniproject.web02.apiPayLoad.ApiResponse;
import miniproject.web02.apiPayLoad.code.status.ErrorStatus;
import miniproject.web02.apiPayLoad.code.status.SuccessStatus;
import miniproject.web02.apiPayLoad.exception.handler.TempHandler;
import miniproject.web02.converter.ReviewSearchConverter;
import miniproject.web02.converter.TotalRatingConverter;
import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import miniproject.web02.repository.LectureRepository;
import miniproject.web02.service.ReviewSearch.ReviewSearchCommandService;
import miniproject.web02.repository.ReviewRepository;
import miniproject.web02.service.lectureSerivce.LectureService;
import miniproject.web02.service.rating.RatingCommandService;
import miniproject.web02.service.reviewService.ReviewService;
import miniproject.web02.web.dto.lectureDTO.LectureRequestDTO;
import miniproject.web02.web.dto.ReviewSearchDTO.ReviewSearchResponseDTO;
import miniproject.web02.web.dto.lectureDTO.LectureResponseDTO;
import miniproject.web02.web.dto.reviewDTO.ReviewFilterRequestDTO;
import miniproject.web02.web.dto.reviewDTO.ReviewResponseDTO;
import miniproject.web02.web.dto.totalRatingDTO.totalRatingResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class ApiController {

    private final LectureRepository lectureRepository;
    private final LectureService lectureService;
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;
    private final RatingCommandService ratingCommandService;
    private final ReviewSearchCommandService reviewSearchCommandService;

    @Operation(summary = "강의 등록 API", description = "새로운 강의 정보를 등록합니다.")
    @PostMapping(value = "/lectures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<LectureResponseDTO.LectureDTO> createLecture(
            @RequestPart("lectureRequest") @Validated LectureRequestDTO lectureRequestDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        // 강의 생성
        LectureResponseDTO.LectureDTO createdLecture = lectureService.createLecture(lectureRequestDTO, image);
        return ApiResponse.of(SuccessStatus.SUCCESS_CREATE_LECTURE, createdLecture);
    }

    @Operation(summary = "총 별점 & 별점 개수 조회 API", description = "해당하는 강의의 총 별점 & 점수 대 별 별점 개수를 조회하는 API")
    @GetMapping("/rating_info/{lectureId}")
    public ApiResponse<totalRatingResponseDTO.getRatingInfoDTO> RatingInfo (@PathVariable(name = "lectureId") Long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() -> new TempHandler(ErrorStatus.LECTURE_NOT_FOUND));
        List<BigDecimal> lectureReviewRatingList = ratingCommandService.getRatingList(lecture);
        if (lectureReviewRatingList.isEmpty()) {
            int[] ratingCounts = {0,0,0,0,0};
            return ApiResponse.onSuccess(TotalRatingConverter.toTotalRatigDTO(lecture,ratingCounts));
        }
        int[] ratingCounts = ratingCommandService.getRatingCounts(lectureReviewRatingList);
        return ApiResponse.onSuccess(TotalRatingConverter.toTotalRatigDTO(lecture,ratingCounts,lectureReviewRatingList));
    }

    @Operation(summary = "강의 정보 조회 API", description = "강의 상세 페이지의 강의 정보 조회")
    @GetMapping("/lecture_info/{lectureId}")
    public ApiResponse<LectureResponseDTO.LectureDTO> getLectureInfo (@PathVariable(name = "lectureId") Long lectureId){
        LectureResponseDTO.LectureDTO lectureDTO = lectureService.getLecture(lectureId);
        return ApiResponse.of(SuccessStatus.SUCCESS_FETCH_LECTURE, lectureDTO);
    }

    @Operation(summary = "특정 강의의 리뷰 목록 조회 API", description = "강의 상세 페이지의 강의 리뷰 목록 조회")
    @GetMapping("/reviews/{lectureId}")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지입니다."),
            @Parameter(name = "rating", description = "별점순"),
            @Parameter(name = "sort", description = "정렬 필드 (추천순-recommend, 최신순-createdAt)")
    })
    public ApiResponse<ReviewResponseDTO.ReviewListDTO> getReviewList (
            @RequestParam Long lectureId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "rating", required = false) Integer rating,
            @RequestParam(name = "sort", defaultValue = "createdAt") String sortField) {
        ReviewFilterRequestDTO filterRequest = new ReviewFilterRequestDTO(rating, sortField);

        // 리뷰 서비스 호출
        ReviewResponseDTO.ReviewListDTO response = reviewService.getLectureReviews(
                lectureId,
                filterRequest.getRating() != null ? filterRequest.getRating().intValue() : null,
                filterRequest.getSort(),
                page
        );

        return ApiResponse.of(SuccessStatus.SUCCESS_FETCH_REVIEW_LIST, response);
    }

    @Operation(summary = "특정 강의의 리뷰 이미지 조회 API", description = "강의 상세 페이지의 최신 리뷰 이미지 3개 조회")
    @GetMapping("/review_img/{lectureId}")
    public ApiResponse<ReviewResponseDTO.ReviewImageListDTO> getReviewImgList (@PathVariable(name = "lectureId") Long lectureId){
        ReviewResponseDTO.ReviewImageListDTO result = reviewService.getReviewImages(lectureId);

        return ApiResponse.of(SuccessStatus.SUCCESS_FETCH_REVIEW_IMAGE, result);
    }
    @Operation(summary = "강의명으로 검색해서 리뷰 조회하는API", description = "강의명으로 검색해서 리뷰를 6개씩 조회합니다")
    @GetMapping("/search")
    @Parameters({
            @Parameter(name = "keyword", description = "검색어, 정확한 강의명을 입력해야합니다."),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지입니다."),
    })
    public ApiResponse<ReviewSearchResponseDTO.ReviewPreviewListDTO> searchReviewByLectureName(@RequestParam("keyword") String keyword, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        Page<Review> reviewList = reviewSearchCommandService.getReviewList(keyword,page);
        return ApiResponse.onSuccess(ReviewSearchConverter.toReviewPreviewListDTO(reviewList));
    }
}
