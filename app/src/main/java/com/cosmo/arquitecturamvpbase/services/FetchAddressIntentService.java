package com.cosmo.arquitecturamvpbase.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.helper.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ana.marrugo on 12/12/2017.
 */

public class FetchAddressIntentService extends IntentService {


    private ResultReceiver resultReceiver;
    private static final String TAG = FetchAddressIntentService.class.getSimpleName();

    public FetchAddressIntentService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        resultReceiver = intent.getParcelableExtra(Constants.RECEIVER);

        if(resultReceiver == null){
            Log.e(TAG, "no puede obtener el receiver");
            return;
        }

        Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);

        if(location == null){
            Log.e(TAG, "localización no proporcionada");
            deliverResultToReceiver(Constants.FAIL_RESULT, "localización no proporcionada");
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses = null;

        try{
            addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), /* max results */ 1);

        }catch (IOException ioex){
            Log.e(TAG, "Servicio no disponible");
            deliverResultToReceiver(Constants.FAIL_RESULT, "Servicio no disponible");
        }catch (Exception ex){
            Log.e(TAG, "Error: " + ex.getMessage());
            deliverResultToReceiver(Constants.FAIL_RESULT, ex.getMessage());
        }

        //validar si obtuvimos direcciones
        if(addresses == null || addresses.size() == 0){
            Log.e(TAG, "Dirección no encontrada");
            deliverResultToReceiver(Constants.FAIL_RESULT, "Dirección no encontrada");
        }else{ //happy way
            Address address = addresses.get(0);


            String addressLine = "";
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                addressLine += address.getAddressLine(i);
            }
            Log.i(TAG, "Dirección encontrada: " + addressLine);

            deliverResultToReceiver(Constants.SUCCESS_RESULT, addressLine);
        }

    }

    private void deliverResultToReceiver(int codeResult, String response) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, response);
        resultReceiver.send(codeResult, bundle);
    }
}
