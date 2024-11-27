package miniproject.web02.service.ReviewSearch;

import lombok.RequiredArgsConstructor;
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
    public Page<Review> getReviewList (String keyword,Integer page){ //리뷰 리스트 가져옴
        Lecture lecture = lectureRepository.findByName("자바"); //쿼리스트링의 키워드로 강의 찾음 <= 여기서 오류나는것같은데...
        Page<Review> reviewPage = reviewSearchRepository.findAllByLecture(lecture,PageRequest.of(page,6));
        return reviewPage;
    }
}
