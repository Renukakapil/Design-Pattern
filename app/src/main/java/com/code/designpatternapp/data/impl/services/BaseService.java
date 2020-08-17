package com.code.designpatternapp.data.impl.services;


import com.code.common.contracts.ICRUDService;
import com.code.common.contracts.IRepository;
import com.code.common.contracts.ISynchronizedModel;
import com.code.common.contracts.Page;
import com.code.common.contracts.PageInput;
import com.code.common.contracts.PageQuery;

public class BaseService <TModel extends ISynchronizedModel> implements ICRUDService<TModel> {

    private IRepository<TModel> local;

    public BaseService(IRepository<TModel> local) {

        this.local = local;
    }

    @Override
    public TModel get(Long id) {
        return local.get(id);
    }

    @Override
    public TModel get(PageQuery query) {
        return local.get(query);
    }

    @Override
    public Page<TModel> search(PageInput input) {
        return local.page(input);
    }

    @Override
    public TModel update(TModel tModel) {
        return local.update(tModel.getId(), tModel);
    }

    @Override
    public TModel create(TModel entity) {
        return local.create(entity);
    }

    @Override
    public void delete(Long id) {
        local.remove(id);
    }

    @Override
    public void deleteAll() {
        local.removeAll();
    }
}
