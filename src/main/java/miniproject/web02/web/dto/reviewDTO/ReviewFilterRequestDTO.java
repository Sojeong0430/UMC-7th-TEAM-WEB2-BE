package miniproject.web02.web.dto.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewFilterRequestDTO {
    private Integer rating;
    private String sort;
}
