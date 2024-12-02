package miniproject.web02.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import miniproject.web02.apiPayLoad.ApiResponse;
import miniproject.web02.apiPayLoad.code.status.SuccessStatus;
import miniproject.web02.repository.LectureRepository;
import miniproject.web02.service.lectureSerivce.LectureService;
import miniproject.web02.web.dto.lectureDTO.LectureRequestDTO;
import miniproject.web02.web.dto.lectureDTO.LectureResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class LectureController {
    private final LectureRepository lectureRepository;
    private final LectureService lectureService;

    @Operation(summary = "강의 등록 API", description = "새로운 강의 정보를 등록합니다.")
    @PostMapping(value = "/lectures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<LectureResponseDTO.LectureDTO> createLecture(
            @RequestPart("lectureRequest") @Validated LectureRequestDTO lectureRequestDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        // 강의 생성
        LectureResponseDTO.LectureDTO createdLecture = lectureService.createLecture(lectureRequestDTO, image);
        return ApiResponse.of(SuccessStatus.SUCCESS_CREATE_LECTURE, createdLecture);
    }

    @Operation(summary = "전체 강의 조회 API", description = "전체 강의 정보 조회")
    @GetMapping("/lectures")
    public ApiResponse<List<LectureResponseDTO.LectureDTO>> getLectureInfo() {
        List<LectureResponseDTO.LectureDTO> lectures = lectureService.getAllLectures();
        return ApiResponse.of(SuccessStatus.SUCCESS_FETCH_LECTURES, lectures);
    }
}
