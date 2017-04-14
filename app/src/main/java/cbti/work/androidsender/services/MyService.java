package cbti.work.androidsender.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import cbti.work.androidsender.receivers.SendDataBroadcastReceiver;

public class MyService extends Service {
    public MyService() {
    }

    private static final String TAG = "ANDROID_SENDER_SERVICE";

    public class MQTTBinder extends Binder {
        public Service getService(){
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        IntentFilter intentf = new IntentFilter();

        //Actions to catch
        intentf.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentf.addAction(Intent.ACTION_BOOT_COMPLETED);

        registerReceiver(new SendDataBroadcastReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged()");
        android.os.Debug.waitForDebugger();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand()");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind called");
        return null;
    }



}
