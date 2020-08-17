package com.code.common.contracts;

public interface AsyncResult<TData> {
    void success(TData data);

    void error(String error);
}

