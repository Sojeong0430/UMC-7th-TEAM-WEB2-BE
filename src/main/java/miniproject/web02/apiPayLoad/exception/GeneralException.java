package miniproject.web02.apiPayLoad.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import miniproject.web02.apiPayLoad.code.BaseErrorCode;
import miniproject.web02.apiPayLoad.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{
    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}