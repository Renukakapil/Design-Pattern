package com.code.common.contracts;

public interface ICustomRemoteApi<TModel> {

    void  get(String url, String token, AsyncResult<TModel> result);

    void page(String url, String token, AsyncResult<TModel> result);

    TModel update(TModel model);

    TModel create(TModel model);

}
