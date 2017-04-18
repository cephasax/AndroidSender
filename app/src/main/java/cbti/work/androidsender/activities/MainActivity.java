package cbti.work.androidsender.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import cbti.work.androidsender.R;
import cbti.work.androidsender.dao.ObdReadingDao;
import cbti.work.androidsender.domain.ObdReading;
import cbti.work.androidsender.services.MyService;
import cbti.work.androidsender.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ANDROID_SENDER_MAIN_ACT";
    ObdReadingDao obdReadingDao;
    ArrayList<ObdReading> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obdReadingDao = new ObdReadingDao(this);
        data = new ArrayList<ObdReading>();

    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

     public void populateDb(View view){
         Utils u = new Utils();
         data = u.createObdReadingArray();

         for(ObdReading obdr: data){
             obdReadingDao.insert(obdr);
         }
     }

     public void listData(View view){
         for(ObdReading obdr: data){
             Log.d(TAG, obdr.toString());
         }
     }

}
