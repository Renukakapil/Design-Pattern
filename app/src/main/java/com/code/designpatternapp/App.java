package com.code.designpatternapp;


import android.app.Application;
import android.content.Context;

import com.code.designpatternapp.data.module.AppComponent;
import com.code.designpatternapp.data.module.AppModule;
import com.code.designpatternapp.data.module.DaggerAppComponent;
import com.code.designpatternapp.data.module.DaoModule;
import com.code.designpatternapp.data.module.MapperModule;
import com.code.designpatternapp.data.module.RepositoryModule;
import com.code.designpatternapp.data.module.ServiceModule;

public class App extends Application {
    private AppComponent component;
    static App app;

    public App(){

    }

    public static App getInstance(){
        if(app ==null){
            app = new App();
        }
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .daoModule(new DaoModule())
                .mapperModule(new MapperModule())
                .repositoryModule(new RepositoryModule())
                .serviceModule(new ServiceModule())
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

}
