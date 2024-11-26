package miniproject.web02.service.reviewService;

import lombok.RequiredArgsConstructor;
import miniproject.web02.apiPayLoad.code.status.ErrorStatus;
import miniproject.web02.apiPayLoad.exception.handler.TempHandler;
import miniproject.web02.domain.Lecture;
import miniproject.web02.domain.Review;
import miniproject.web02.repository.LectureRepository;
import miniproject.web02.repository.ReviewRepository;
import miniproject.web02.web.dto.reviewDTO.ReviewResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final LectureRepository lectureRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDTO.ReviewListDTO getLectureReviews(long lectureId, Integer rating, String sortField, Integer page) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.LECTURE_NOT_FOUND));

        Pageable pageable;
        if ("recommend".equals(sortField)) {
            pageable = PageRequest.of(page, 5,
                    Sort.by(Sort.Order.desc("recommend"), Sort.Order.desc("createdAt")));
        } else {
            pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, sortField));
        }

        Page<Review> reviews = reviewRepository.findByLecture(lecture, pageable);
        List<Review> reviewList = new ArrayList<>(reviews.getContent());

        if (rating != null) {
            reviewList = reviewList.stream()
                    .filter(review -> review.getRating().equals(rating))
                    .collect(Collectors.toList());
        }

        List<ReviewResponseDTO.ReviewDTO> reviewDTOList = reviewList.stream()
                .map(review -> {
                    return ReviewResponseDTO.ReviewDTO.builder()
                            .reviewId(review.getReviewId())
                            .rating(review.getRating())
                            .studyTime(review.getStudyTime())
                            .likes(review.getLikes())
                            .content(review.getContent())
                            .createdAt(review.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewListDTO.builder()
                .reviewList(reviewDTOList)
                .listSize(reviewDTOList.size())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }
}
