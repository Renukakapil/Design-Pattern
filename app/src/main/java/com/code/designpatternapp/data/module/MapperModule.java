package com.code.designpatternapp.data.module;
import com.code.common.contracts.IModelMapper;
import com.code.designpatternapp.data.contracts.models.AddressModel;
import com.code.designpatternapp.data.impl.entities.Address;
import com.code.designpatternapp.data.impl.mappers.AddressMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {

    @Provides
    IModelMapper<Address, AddressModel> providesAddressMapper(){
        return new AddressMapper();
    }


}
