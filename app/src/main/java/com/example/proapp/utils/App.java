package com.example.proapp.utils;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ObjectBox.init(this);
    }
}
