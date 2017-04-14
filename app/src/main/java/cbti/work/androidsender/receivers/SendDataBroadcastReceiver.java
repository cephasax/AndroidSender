package cbti.work.androidsender.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import cbti.work.androidsender.services.MyService;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class SendDataBroadcastReceiver extends BroadcastReceiver {

    //Chama o service para mandar dados para a nuvem caso tenha conexao wifi

    private static final String TAG = "SEND_DATA_B_RECEIVER";
    private boolean hasWifi = false;
    private ConnectivityManager mConnMan;

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean hasConnectivity = false;
        boolean hasChanged = false;

        mConnMan = (ConnectivityManager)  context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnMan.getActiveNetworkInfo();

        if ( info.getTypeName().equalsIgnoreCase("WIFI") ){
            if((info.isConnected() != hasWifi)){
                hasChanged = true;
                hasWifi = info.isConnected();
            }
            Log.d(TAG, info.getTypeName() + " is " + info.isConnected());
        }

        hasConnectivity = hasWifi;
        Log.v(TAG, "hasConn: " + hasConnectivity + " hasChange: " + hasChanged);
        if (hasConnectivity && hasChanged) {

            // TEM CONEXÃO
            Log.d(TAG, "WIFI IS CONNECTED");

            context.startService(new Intent(context, MyService.class));
        }
        else {
            Log.d(TAG, "WIFI IS DISCONNECTED");
            // NÃO TEM CONEXÃO
        }
    }
}
