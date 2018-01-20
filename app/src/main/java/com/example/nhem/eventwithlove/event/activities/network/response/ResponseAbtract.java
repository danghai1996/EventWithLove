package com.example.nhem.eventwithlove.event.activities.network.response;

/**
 * Created by Hau on 1/20/2018.
 */

public abstract class ResponseAbtract<T> {
    protected int code;
    protected T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseAbtract{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}
