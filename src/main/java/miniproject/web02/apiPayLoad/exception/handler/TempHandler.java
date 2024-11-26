package miniproject.web02.apiPayLoad.exception.handler;

import miniproject.web02.apiPayLoad.code.BaseErrorCode;
import miniproject.web02.apiPayLoad.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) { super(errorCode); }
}
