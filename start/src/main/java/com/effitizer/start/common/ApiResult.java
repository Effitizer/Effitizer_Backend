package com.effitizer.start.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ApiResult<T> {
    @Autowired boolean success;
    @Autowired T response;
    @Autowired ApiError apiError;

    private ApiResult(boolean success, T response, ApiError apiError) {
        this.success = success;
        this.response = response;
        this.apiError = apiError;
    }

    public static <T> ApiResult<T> OK(T response){
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> ERROR(String errorMessage, HttpStatus status){
        return new ApiResult<>(false, null, new ApiError(errorMessage, status));
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResponse() {
        return response;
    }

    public ApiError getApiError() {
        return apiError;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "success=" + success +
                ", response=" + response +
                ", apiError=" + apiError +
                '}';
    }

}
