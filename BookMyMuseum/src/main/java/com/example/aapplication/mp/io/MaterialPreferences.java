package com.example.aapplication.mp.io;

import android.content.Context;


public class MaterialPreferences {

    private static final MaterialPreferences instance = new MaterialPreferences();

    public static MaterialPreferences instance() {
        return instance;
    }

    public static UserInputModule getUserInputModule(Context context) {
        return instance.userInputModuleFactory.create(context);
    }

    public static StorageModule getStorageModule(Context context) {
        return instance.storageModuleFactory.create(context);
    }

    private UserInputModule.Factory userInputModuleFactory;
    private StorageModule.Factory storageModuleFactory;

    private MaterialPreferences() {
        userInputModuleFactory = new StandardUserInputFactory();
        storageModuleFactory = new SharedPrefsStorageFactory(null);
    }

    public MaterialPreferences setUserInputModule(UserInputModule.Factory factory) {
        userInputModuleFactory = factory;
        return this;
    }

    public MaterialPreferences setStorageModule(StorageModule.Factory factory) {
        storageModuleFactory = factory;
        return this;
    }

    public void setDefault() {
        userInputModuleFactory = new StandardUserInputFactory();
        storageModuleFactory = new SharedPrefsStorageFactory(null);
    }


    private static class StandardUserInputFactory implements UserInputModule.Factory {
        @Override
        public UserInputModule create(Context context) {
            return new StandardUserInputModule(context);
        }
    }
}
