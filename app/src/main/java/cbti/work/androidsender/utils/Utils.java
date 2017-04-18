package cbti.work.androidsender.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import cbti.work.androidsender.domain.ObdReading;

/**
 * Created by Cephas on 04/04/2017.
 */

public class Utils {

    private HashMap<String, String> readingsMap;

    public Utils(){
         this.readingsMap = new HashMap<String, String>();
    }

    public void putReadingOnMap(String key, String value) {
        this.readingsMap.put(key, value);
    }

    public String getJsonStringFromMap(){
        Gson gson = new Gson();
        String json = gson.toJson(this.readingsMap);
        return json;
    }

    public HashMap<String, String> getReadingsMap(){
        if(readingsMap.size() != 0){
            return this.readingsMap;
        }
        else{
            return null;
        }
    }

    public HashMap<String, String> getMapFromJsonString(String readingString){
        Gson gson = new Gson();
        Type typeOfHashMap = new TypeToken<HashMap<String, String>>() { }.getType();
        HashMap<String, String> map = gson.fromJson(readingString, typeOfHashMap); // This type must match TypeToken
       return map;
    }

    public ArrayList<ObdReading> createObdReadingArray(){

        ArrayList<ObdReading> data = new ArrayList<ObdReading>();
        for (int i = 1; i <= 30; i++ ){
            ObdReading obdr = new ObdReading();
            Utils u = new Utils();

            //Timestamp
            obdr.setTimestamp(1492496663);

            //vehicleId
            if(i % 2 == 1){
                obdr.setVehicleId("abcd1234");
            }
            else{
                obdr.setVehicleId("abcd5555");
            }

            //latitude
            obdr.setLatitude(5467.23);

            //longitude
            obdr.setLongitude(3426.135);

            //altitude
            obdr.setAltitude(112.128);

            //readings
            for(int cont = 0; cont <= i; cont++){
                u.putReadingOnMap(String.valueOf(cont), String.valueOf(i*cont));
            }
            obdr.setReadings(u.getJsonStringFromMap());

            //insert on array
            data.add(obdr);
        }
        return data;
    }

}
