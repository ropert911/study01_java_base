package org.sang.requestLine;

public class ResponseInfo<T> {
    /**
     * 类型 （可为空）
     */
    private String type;

    /**
     * 是否成功 （不能为空）
     */
    private Boolean success;

    /**
     * api返回的数据 （可为空）
     */
    private T data;

    /**
     * 错误信息（可为空）
     */
    private Error error;

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

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "type='" + type + '\'' +
                ", success='" + success + '\'' +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}