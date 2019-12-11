package com.yur0k.homework4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";
//    MyBinder binder = new MyBinder();

    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        runSecond();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void runSecond() {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i<=5; i++) {
                    Log.d(LOG_TAG, "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }

//    public IBinder onBind(Intent arg0) {
//        return binder;
//    }
//
//    class MyBinder extends Binder {
//        MyService getService() {
//            return MyService.this;
//        }
//    }

}
