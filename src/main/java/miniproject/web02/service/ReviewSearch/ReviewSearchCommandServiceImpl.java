package miniproject.web02.service.ReviewSearch;

import lombok.RequiredArgsConstructor;
import miniproject.web02.apiPayLoad.code.status.ErrorStatus;
import miniproject.web02.apiPayLoad.exception.handler.TempHandler;
import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import miniproject.web02.repository.LectureRepository;
import miniproject.web02.repository.ReviewSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewSearchCommandServiceImpl implements ReviewSearchCommandService{

    private final ReviewSearchRepository reviewSearchRepository;
    private final LectureRepository lectureRepository;
    @Override
    public Page<Review> getReviewList (String keyword,Integer page){
        Lecture lecture = lectureRepository.findByName(keyword).orElseThrow(() -> new TempHandler(ErrorStatus.LECTURE_NOT_FOUND));
        Page<Review> reviewPage = reviewSearchRepository.findAllByLecture(lecture,PageRequest.of(page,6));
        return reviewPage;
    }
}
