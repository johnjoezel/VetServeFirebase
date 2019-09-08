package com.example.vetservefirebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String TAG = "FirebaseMessaging";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
     Log.d("msg", "onMessageReceived: " + remoteMessage.getData().get("message"));
         String channelId = "Default";
         NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                 .setSmallIcon(R.mipmap.ic_launcher)
                 .setContentTitle(remoteMessage.getNotification().getTitle())
                 .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true);
         NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
             manager.createNotificationChannel(channel);
         }
         manager.notify(0, builder.build());
    }


    @Override
    public void onNewToken(String token) {
        String atay = token;
        Log.d(TAG, "Refreshed token: " + atay);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
    }

}
