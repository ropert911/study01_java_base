package org.sang;

import java.util.List;

/**
 * Created by sk-qianxiao on 2018/8/7.
 */
public class ResponseInfo<T> {
    private T data;
    private String type;
    private Boolean success;
    private ResponseError error;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseError getError() {
        return error;
    }

    public void setError(ResponseError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "type='" + type + '\'' +
                ", success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
