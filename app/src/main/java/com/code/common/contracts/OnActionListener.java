package com.code.common.contracts;

public interface OnActionListener<TModel> {
    void notify(TModel model);
//    void onDeSelect(TModel model);
}
