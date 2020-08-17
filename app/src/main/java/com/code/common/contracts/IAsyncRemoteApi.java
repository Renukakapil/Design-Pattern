package com.code.common.contracts;


import android.media.Image;

import com.code.designpatternapp.data.contracts.models.ImageModel;

import java.io.File;

public interface IAsyncRemoteApi<TModel extends IModel> {

    IAsyncResponse<TModel> get(final String id);

    void get(AsyncResult<TModel> result);

    void get(final String id, AsyncResult<TModel> result);

    void get(final String id, String action, AsyncResult<TModel> result);

    void get(final String id, String action, String token, AsyncResult<TModel> result);


    IAsyncResponse<TModel> update(final TModel model, String token);

    void update(final TModel model, AsyncResult<TModel> result);

    IAsyncResponse<Page<TModel>> page(final PageInput input);


    void page(final PageInput input, AsyncResult<Page<TModel>> result);


    void page(final PageInput input, String action, AsyncResult<Page<TModel>> result);

    void page(final PageInput input, String action, String token, AsyncResult<Page<TModel>> result);

    void create(final TModel model, AsyncResult<TModel> result);

    void create(TModel model, String action, AsyncResult<TModel> result);

    void create(TModel model, String token, String action, AsyncResult<TModel> result);

    void delete(Long id, String token, AsyncNotify result);

    void upload(String action, File file, AsyncResult<ImageModel> result);

}
