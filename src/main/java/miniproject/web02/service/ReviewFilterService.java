package miniproject.web02.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import miniproject.web02.domain.Review;
import miniproject.web02.repository.ReviewRepository;
import miniproject.web02.repository.ReviewSpecification;
import miniproject.web02.web.dto.review.FilterRequestDTO;
import miniproject.web02.web.dto.review.ReviewFilterResponseDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewFilterService {

    private final ReviewRepository reviewRepository;

    public List<ReviewFilterResponseDTO> filterAndSortReviews(FilterRequestDTO filterRequestDTO) {
        Specification<Review> spec = Specification.where(null);

        // 필터링 조건 추가
        if (filterRequestDTO.getCategory() != null) {
            spec = spec.and(ReviewSpecification.hasCategory(filterRequestDTO.getCategory()));
        }
        if (filterRequestDTO.getLevel() != null) {
            spec = spec.and(ReviewSpecification.hasLevel(filterRequestDTO.getLevel()));
        }
        if (filterRequestDTO.getStudyTime() != null) {
            spec = spec.and(ReviewSpecification.hasStudyTime(filterRequestDTO.getStudyTime()));
        }

        // 정렬 조건 추가
        List<Review> reviews = reviewRepository.findAll(spec, getSortByRequest(filterRequestDTO.getSort()));

        // Review 객체를 DTO로 변환
        return reviews.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private Sort getSortByRequest(String sort) {
        if ("recommended".equals(sort)) {
            return Sort.by(Sort.Order.desc("likes"));
        } else if ("latest".equals(sort)) {
            return Sort.by(Sort.Order.desc("createdAt"));
        }
        return Sort.unsorted();
    }

    private ReviewFilterResponseDTO toDTO(Review review) {
        return new ReviewFilterResponseDTO(
                review.getReviewId(),
                review.getLecture().getName(),
                review.getLecture().getPlatform(),
                review.getLecture().getTeacher(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt(),
                review.getLecture().getCategory(),
                review.getLikes()
        );
    }
}