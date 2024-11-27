package miniproject.web02.service.lectureSerivce;

import lombok.RequiredArgsConstructor;
import miniproject.web02.apiPayLoad.code.status.ErrorStatus;
import miniproject.web02.apiPayLoad.exception.handler.TempHandler;
import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.LectureImage;
import miniproject.web02.repository.LectureImageRepository;
import miniproject.web02.repository.LectureRepository;
import miniproject.web02.web.dto.lectureDTO.LectureResponseDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final LectureImageRepository lectureImageRepository;

    @Override
    public LectureResponseDTO.LectureDTO getLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.LECTURE_NOT_FOUND));

        String imageUrl = lectureImageRepository.findFirstByLecture(lecture)
                .map(LectureImage::getImageUrl)
                .orElse(null);

        return LectureResponseDTO.LectureDTO.builder()
                .lectureId(lecture.getLectureID())
                .lectureName(lecture.getName())
                .platform(lecture.getPlatform().toString())
                .teacher(lecture.getTeacher())
                .imageUrl(imageUrl)
                .build();
    }
}
