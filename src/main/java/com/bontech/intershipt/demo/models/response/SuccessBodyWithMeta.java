package com.bontech.intershipt.demo.models.response;

public class SuccessBodyWithMeta<T, M> {
    private final boolean success = true;
    private M meta;
    private final T data;

    public SuccessBodyWithMeta(T data) {
        this.data = data;
    }

    public SuccessBodyWithMeta(T data, M meta) {
        this.data = data;
        this.meta = meta;
    }
    public boolean getSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public M getMeta() { return this.meta; }

    public void setMeta(M meta) { this.meta = meta; }
}
