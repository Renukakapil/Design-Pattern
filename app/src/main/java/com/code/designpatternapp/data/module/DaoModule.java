package com.code.designpatternapp.data.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.code.designpatternapp.data.impl.entities.AddressDao;
import com.code.designpatternapp.data.impl.entities.DaoMaster;
import com.code.designpatternapp.data.impl.entities.DaoSession;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    static SQLiteDatabase _database;
    static DaoMaster _daoMaster;
    static DaoSession _daoSession;

    @Provides
    SQLiteDatabase providesSQLiteDatabase(Context context) {
        if (_database == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "Dpa-db", null);
            _database = helper.getWritableDatabase();
        }

        return _database;
    }

    @Provides
    DaoMaster providesDaoMaster(SQLiteDatabase database) {
        if (_daoMaster == null) {
            _daoMaster = new DaoMaster(database);
        }
        return _daoMaster;
    }

    @Provides
    DaoSession providesDaoSession(DaoMaster daoMaster) {
        if (_daoSession == null) {
            _daoSession = daoMaster.newSession();
        }
        return _daoSession;
    }


    @Provides
    AddressDao providesAddressDao(DaoSession daoSession){
        return daoSession.getAddressDao();
    }



}


