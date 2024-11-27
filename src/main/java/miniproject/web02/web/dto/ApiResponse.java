package miniproject.web02.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message","result"}) //필드 순서 지정
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값 필드는 직렬화하지 않음
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    private final String code;
    private final String message;

    private final T result;
}

