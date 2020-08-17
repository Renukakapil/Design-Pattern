package com.code.common.contracts;


public interface IModelMapper<TEntity, TModel> {

    TModel Map(TEntity entity);
}
