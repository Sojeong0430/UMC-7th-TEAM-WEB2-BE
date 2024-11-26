package miniproject.web02.service.lectureSerivce;

import miniproject.web02.web.dto.lectureDTO.LectureResponseDTO;

public interface LectureService {
    LectureResponseDTO.LectureDTO getLecture(Long lectureId);
}
