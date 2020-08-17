package com.code.designpatternapp.data.impl.services;

import com.code.common.contracts.AsyncResult;
import com.code.common.contracts.IAsyncRemoteApi;
import com.code.common.contracts.IRepository;
import com.code.common.contracts.Page;
import com.code.common.contracts.PageInput;
import com.code.designpatternapp.data.contracts.models.AddressModel;
import com.code.designpatternapp.data.contracts.services.IAddressService;

public class AddressService extends BaseService<AddressModel> implements IAddressService {

    IAsyncRemoteApi<AddressModel> mRemoteApi;
    IRepository<AddressModel> mLocal;

    public AddressService(IRepository<AddressModel> local, IAsyncRemoteApi<AddressModel> remoteApi) {
        super(local);
        mLocal=local;
        mRemoteApi=remoteApi;
    }

    @Override
    public void createAddress(AddressModel addressModel, AsyncResult<AddressModel> result){
        mRemoteApi.create(addressModel, result);
    }

    @Override
    public long saveAddress(AddressModel addressModel) {
        return create(addressModel).getId();
    }

    @Override
    public void getMyAddress(AsyncResult<Page<AddressModel>> result) {
        mRemoteApi.page(new PageInput(),result);

    }

    @Override
    public void updateAddress(AddressModel addressModel, AsyncResult<AddressModel> result) {
        mRemoteApi.update(addressModel,result);
    }

}
