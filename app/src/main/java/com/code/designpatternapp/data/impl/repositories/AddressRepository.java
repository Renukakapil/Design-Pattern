package com.code.designpatternapp.data.impl.repositories;


import com.code.common.contracts.IModelMapper;
import com.code.common.contracts.PageQuery;
import com.code.designpatternapp.data.contracts.models.AddressModel;
import com.code.designpatternapp.data.impl.entities.Address;
import com.code.designpatternapp.data.impl.entities.AddressDao;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.query.QueryBuilder;


public class AddressRepository extends BaseRepository<Address, AddressModel> {

    AbstractDao<Address, Long> dao;

    public AddressRepository(IModelMapper<Address, AddressModel> mapper, AbstractDao<Address, Long> dao) {
        super(mapper, dao);
        this.dao=dao;
    }

    @Override
    protected QueryBuilder<Address> query(Long id) {

        return dao.queryBuilder().where(AddressDao.Properties.Id.eq(id));
    }

    @Override
    protected QueryBuilder<Address> query(PageQuery query) {
        QueryBuilder<Address> queryBuilder=dao.queryBuilder();

        if(query.contains("all")){
            queryBuilder.list();
        }
        return queryBuilder;
    }

    @Override
    protected void map(AddressModel model) {

    }

    @Override
    protected void map(Address address, AddressModel model) {
        address.setId(model.getId());
        address.setName(model.getName());
        address.setNickName(model.getNickName());
        address.setLine1(model.getLine1());
        address.setLine2(model.getLine2());
        address.setState(model.getState());
        address.setPinCode(model.getPinCode());
        address.setPhone(model.getPhone());
        address.setServerId(model.getServerId());
        address.setEmail(model.getEmail());
        address.setCity(model.getCity());
    }

    @Override
    protected Address newEntity() {
        return new Address();
    }
}
