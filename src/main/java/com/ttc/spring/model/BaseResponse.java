package com.ttc.spring.model;

import com.dslplatform.json.CompiledJson;
import com.ttc.spring.exception.BusinessErrorCode;
import com.ttc.spring.exception.BusinessException;
import com.ttc.spring.utils.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private T data;
    private Metadata meta = new Metadata();

    @CompiledJson
    BaseResponse(T data, Metadata meta) {
        this.data = data;
        this.meta = meta;
    }

    public BaseResponse() {
    }

    public static <T> BaseResponse<T> ofSucceeded() {
        return ofSucceeded((T) null);
    }

    @SuppressWarnings("unchecked")
    public static <T> BaseResponse<T> ofSucceeded(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = Constants.OK_CODE;
        response.meta.message = "Thành công";
        return response;
    }

    public static <T> BaseResponse<List<T>> ofSucceeded(Page<T> data) {
        BaseResponse<List<T>> response = new BaseResponse<>();
        response.data = data.getContent();
        response.meta.code = Constants.OK_CODE;
        response.meta.message = "Thành công";
        response.meta.page = data.getNumber();
        response.meta.size = data.getSize();
        response.meta.total = data.getTotalElements();
        return response;
    }

    public static <T> BaseResponse<List<T>> ofSucceeded(Page<T> data, Long totalErrors) {
        BaseResponse<List<T>> response = ofSucceeded(data);
        response.meta.totalErrors = totalErrors;
        return response;
    }

    public static BaseResponse<Void> ofFailed(BusinessErrorCode errorCode) {
        return ofFailed(errorCode, (String) null);
    }


    public static BaseResponse<Void> ofFailed(BusinessErrorCode errorCode, String message) {
        BaseResponse<Void> response = new BaseResponse<>();
        response.meta.code = Constants.PREFIX_RESPONSE_CODE + "-" + Constants.GROUP_CODE_SUCCESS + "-" + errorCode.getCode();
        response.meta.message = message;
        return response;
    }

    public static BaseResponse<Void> ofFailed(BusinessException exception) {
        return ofFailed(exception.getErrorCode(), exception.getMessage());
    }

    public T getData() {
        return data;
    }

    public Metadata getMeta() {
        return meta;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        String code;
        Integer page;
        Integer size;
        Long total;
        Long totalErrors;
        String message;

        public Metadata() {
        }

        @CompiledJson
        public Metadata(String code, Integer page, Integer size, Long total, String message) {
            this.code = code;
            this.page = page;
            this.size = size;
            this.total = total;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public Integer getPage() {
            return page;
        }

        public Integer getSize() {
            return size;
        }

        public Long getTotal() {
            return total;
        }

        public String getMessage() {
            return message;
        }

        public Long getTotalErrors() {
            return totalErrors;
        }

    }
}
