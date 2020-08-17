package com.code.designpatternapp.data.contracts.services;

import com.code.common.contracts.AsyncResult;
import com.code.common.contracts.ICRUDService;
import com.code.common.contracts.Page;
import com.code.designpatternapp.data.contracts.models.AddressModel;


public interface IAddressService extends ICRUDService<AddressModel> {

    void createAddress(AddressModel addressModel, AsyncResult<AddressModel> result);

    long saveAddress(AddressModel addressModel);

    void getMyAddress(AsyncResult<Page<AddressModel>> result);

    void updateAddress(AddressModel addressModel, AsyncResult<AddressModel> result);
}
