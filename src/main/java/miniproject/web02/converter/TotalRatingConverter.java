package miniproject.web02.converter;

import miniproject.web02.domain.Lecture;
import miniproject.web02.web.dto.totalRatingDTO.totalRatingResponseDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TotalRatingConverter {

    public static BigDecimal totalRatingcalculator (List<BigDecimal> lectureReviewRatingList){
        BigDecimal totalSum = lectureReviewRatingList.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = totalSum.divide(new BigDecimal(lectureReviewRatingList.size()), 2, BigDecimal.ROUND_HALF_UP);
        return average;
    } //평균 구하는 메서드

    public static totalRatingResponseDTO.getRatingInfoDTO toTotalRatigDTO(Lecture lecture, int[] ratingCounts, List<BigDecimal> lectureReviewRatingList) {

        int totalSum = Arrays.stream(ratingCounts).sum();

        return totalRatingResponseDTO.getRatingInfoDTO.builder()
                .lectureId(lecture.getLectureID())
                .lectureName(lecture.getName())
                .reviewCounts(totalSum)
                .totalRating(TotalRatingConverter.totalRatingcalculator(lectureReviewRatingList))
                .ratingCounts(ratingCounts)
                .build();
    } // getRatingInfoDTO DTO 만들기

    public static totalRatingResponseDTO.getRatingInfoDTO toTotalRatigDTO(Lecture lecture, int[] ratingCounts) {

        int totalSum = Arrays.stream(ratingCounts).sum();

        return totalRatingResponseDTO.getRatingInfoDTO.builder()
                .lectureId(lecture.getLectureID())
                .lectureName(lecture.getName())
                .reviewCounts(totalSum)
                .ratingCounts(ratingCounts)
                .build();
    } // getRatingInfoDTO DTO 만들기
}