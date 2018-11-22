package com.ahmaddudayef.basicsample;

import android.app.Application;

import com.ahmaddudayef.basicsample.db.AppDatabase;

/**
 * Created by Ahmad Dudayef on 11/22/2018.
 */

public class BasicApp extends Application {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository(){
        return DataRepository.getsInstance(getDatabase());
    }
}
