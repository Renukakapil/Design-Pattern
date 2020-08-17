package com.code.designpatternapp.data.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.code.common.contracts.DataModel;
import com.code.common.contracts.IAsyncRemoteApi;
import com.code.common.contracts.IModelMapper;
import com.code.common.contracts.IRepository;
import com.code.common.contracts.Page;
import com.code.common.repositories.AsyncRemoteApi;
import com.code.designpatternapp.data.contracts.models.AddressModel;
import com.code.designpatternapp.data.impl.entities.Address;
import com.code.designpatternapp.data.impl.entities.AddressDao;
import com.code.designpatternapp.data.impl.repositories.AddressRepository;
import com.google.gson.reflect.TypeToken;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    IRepository<AddressModel> providesAddressRepository(AddressDao addressDao
            , IModelMapper<Address, AddressModel> mapper){
        return new AddressRepository(mapper,addressDao);
    }

    @Provides
    IAsyncRemoteApi<AddressModel> providesAsyncAddressRepository(Context context, SQLiteDatabase sqLiteDatabase){
        return new AsyncRemoteApi<>(context,"addresses",new TypeToken<AddressModel>(){}.getType(),
                new TypeToken<Page<AddressModel>>(){}.getType(),
                new TypeToken<DataModel<AddressModel>>(){}.getType(),sqLiteDatabase);
    }
}
