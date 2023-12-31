package com.example.EcoHack.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private HttpStatus httpStatus;
    private int httpStatusCode;
    private String exceptionId = "";
    private List<String> exceptionStack = new ArrayList<>(); // make sure to call buildExceptionStack if modified
    @Setter
    private String message; // descriptive msg
    @Setter
    private Exception rootException; // root ex if we are encapsulating one
    @Setter
    private HashMap<String, Object> details = new HashMap<>(); // extra details
    @Setter
    private ArrayList<String> detailsList = new ArrayList<>();

    @Setter
    private boolean suppressRootException = true;
    @Setter
    private boolean suppressRootExceptionMessage = true;
    @Setter
    private boolean exposeExtraFieldsAsDetails = true;

    private boolean isInternalServerException = false;
    private String internalServerExceptionLogFile = null;
    private String internalServerExceptionURL = null;

    public BaseException(HttpStatus httpStatus, String message, String customExceptionId, boolean suppressRootException, boolean suppressRootExceptionMessage) {
        setHttpStatus(httpStatus);
        buildExceptionStack(customExceptionId);
        setMessage(message);
        setSuppressRootException(suppressRootException);
        setSuppressRootExceptionMessage(suppressRootExceptionMessage);
    }

    public BaseException(HttpStatus httpStatus, String message, String customExceptionId, boolean suppressRootExceptionMessage) {
        this(httpStatus, message, customExceptionId, true, false);
    }

    public BaseException(HttpStatus httpStatus, String message, String customExceptionId) {
        this(httpStatus, message, customExceptionId, true, true);
    }

    public BaseException(HttpStatus httpStatus, String message) {
        this(httpStatus, message, null, true, true);
    }

    public BaseException(HttpStatus httpStatus) {
        this(httpStatus, null);
    }

    public BaseException() {
        buildExceptionStack(null);
        buildAsInternalServerException();
    }

    private void buildExceptionStack(String customExceptionId) {
        Class<?> class_ = getClass();
        StringBuilder id = new StringBuilder();
        if (customExceptionId != null && customExceptionId.trim().length() != 0) {
            exceptionStack.add(0, class_.getSimpleName());
            id.insert(0, class_.getSimpleName());
        }
        while (class_ != null && class_ != BaseException.class) {
            if (id.length() > 0)
                id.insert(0, ".");
            exceptionStack.add(0, class_.getSimpleName());
            id.insert(0, class_.getSimpleName());
            class_ = class_.getSuperclass();
        }
        exceptionId = id.toString();
    }

    public void setCustomExceptionId(String customExceptionId) {
        buildExceptionStack(customExceptionId);
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.httpStatusCode = httpStatus.value();
    }

    public void addDetail(String detail) {
        detailsList.add(detail);
    }

    public void putDetail(String key, Object value) {
        details.put(key, value);
    }

    public void buildAsInternalServerException() {
        isInternalServerException = true;
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR); // TODO
    }

    public boolean isInternalServerException() {
        return isInternalServerException;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "httpStatus=" + httpStatus +
                ", httpStatusCode=" + httpStatusCode +
                ", exceptionId='" + exceptionId + '\'' +
                ", exceptionStack=" + exceptionStack +
                ", message='" + message + '\'' +
                ", rootException=" + rootException +
                ", details=" + details +
                ", detailsList=" + detailsList +
                ", suppressRootException=" + suppressRootException +
                ", suppressRootExceptionMessage=" + suppressRootExceptionMessage +
                ", exposeExtraFieldsAsDetails=" + exposeExtraFieldsAsDetails +
                ", isInternalServerException=" + isInternalServerException +
                ", internalServerExceptionLogFile='" + internalServerExceptionLogFile + '\'' +
                ", internalServerExceptionURL='" + internalServerExceptionURL + '\'' +
                '}';
    }

    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(this);
    }

    public ResponseEntity<Object> toResponseEntity() {
        return toErrorResponse().toResponseEntity();
    }
}
