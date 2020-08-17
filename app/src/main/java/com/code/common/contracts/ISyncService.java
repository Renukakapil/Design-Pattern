package com.code.common.contracts;


import java.util.Date;

public interface ISyncService<TModel> {
    void push(PageInput input);

    void push(PageInput input, Date timeStamp);

    void push(PageInput input, Date timeStamp, String action, String token);

    void push(PageInput input, Date timeStamp, String action);

//    void pull(PageQuery query, Date timeStamp, AsyncResult<Page<TModel>> result);

    IAsyncResponse<Integer> pullApp(PageInput pageInput, Date timeStamp, String token);

    IAsyncResponse<Integer> pullEdu(PageInput pageInput, Date timeStamp);
}
