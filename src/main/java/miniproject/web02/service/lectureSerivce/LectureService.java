package miniproject.web02.service.lectureSerivce;

import miniproject.web02.web.dto.lectureDTO.LectureRequestDTO;
import miniproject.web02.web.dto.lectureDTO.LectureResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface LectureService {
    LectureResponseDTO.LectureDTO getLecture(Long lectureId);

    //LectureResponseDTO createLecture(LectureRequestDTO lectureRequestDto, MultipartFile image);
    //LectureResponseDTO.LectureDTO createLecture(LectureRequestDTO lectureRequestDto);
    LectureResponseDTO.LectureDTO createLecture(LectureRequestDTO lectureRequestDTO, MultipartFile image);
}
