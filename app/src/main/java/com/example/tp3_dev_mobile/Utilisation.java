package com.example.tp3_dev_mobile;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class Utilisation implements LifecycleObserver {

    private final String TAG = this.getClass().getSimpleName();
    private static int counter = 0;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeEvent() {
       Log.i(TAG, " Observer onResume");
       counter++;
    }

    public static int nombreUtilisation(){
        return counter;
    }
}
