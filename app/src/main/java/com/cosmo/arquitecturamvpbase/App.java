package com.cosmo.arquitecturamvpbase;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by ana.marrugo on 07/11/2017.
 */

public class App extends Application {
  private Tracker mTracker;

  synchronized  public Tracker  getDefauldTracker(){
      if(mTracker==null){
          GoogleAnalytics analytics= GoogleAnalytics.getInstance(this);
         // mTracker=analytics.newTracker(R.xml.global_tracker);
      }

      return mTracker;
  }
    @Override
    public void onCreate() {
        super.onCreate();
        settingOneSignal();
    }

    private void settingOneSignal(){
        OneSignal.startInit(this).autoPromptLocation(false).
                setNotificationReceivedHandler(new NotificationReceiverHandler()).
                setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenResult result) {

                    }
                })
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).init();

    }

    private class NotificationReceiverHandler implements  OneSignal.NotificationReceivedHandler{


        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data=notification.payload.additionalData;
            String notificationID=notification.payload.notificationID;
            String title=notification.payload.title;
            Log.i("ONESIGNAL","notificacionID "+ data.toString());
            if(data!=null){
                String customKey=data.optString("customkey", null);
                if(customKey!=null){
                    Log.i("ONESIGNAL","token custokey"+ customKey);
                }
            }
        }
    }



}
