package com.code.designpatternapp.data.module;


import com.code.common.contracts.IAsyncRemoteApi;
import com.code.common.contracts.IRepository;
import com.code.designpatternapp.data.contracts.models.AddressModel;
import com.code.designpatternapp.data.contracts.services.IAddressService;
import com.code.designpatternapp.data.impl.services.AddressService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {


    @Provides
    IAddressService provideAddressService(IRepository<AddressModel> local, IAsyncRemoteApi<AddressModel> remoteApi){
        return new AddressService(local,remoteApi);
    }


}
