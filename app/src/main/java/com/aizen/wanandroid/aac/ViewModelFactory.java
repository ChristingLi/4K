package com.aizen.wanandroid.aac;

import android.app.Application;
import android.os.Bundle;

import com.aizen.wanandroid.MyApp;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by ld on 2018/12/24.
 *
 * @author ld
 * @date 2018/12/24
 * 描    述：
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private Bundle argument;

    public ViewModelFactory(Application application, Bundle argument) {
        mApplication = application;
        this.argument = argument;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class <T> modelClass) {
        Class[] param = new Class[]{MyApp.class, Bundle.class};
        T model = null;
        try {
            model = modelClass.getConstructor(param[0], param[1]).newInstance(this.mApplication, this.argument);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return model;
    }
}
