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
    private ConnectivityManager mConnMan;

    @Override
    public void onReceive(Context context, Intent intent) {

        mConnMan = (ConnectivityManager)  context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnMan.getActiveNetworkInfo();

        if ( (info != null) && (info.getTypeName().equalsIgnoreCase("WIFI")) ){
            if(info.isConnected() == true){

                // TEM CONEXÃO WIFI
                Log.d(TAG, "WIFI IS CONNECTED");

                Intent background = new Intent(context, MyService.class);
                context.startService(background);
            }
        }
        else {
            Log.d(TAG, "WIFI IS DISCONNECTED");
            // NÃO TEM CONEXÃO WIFI
        }
    }
}
