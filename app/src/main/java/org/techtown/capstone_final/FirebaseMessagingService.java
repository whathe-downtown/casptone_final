package org.techtown.capstone_final;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /*푸시 알림 정보를 받아서 메소드로 넘긴다.*/
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        //Handle FCM Message
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        //Check if message contains a data payload.
        if(remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if(true){
                scheduleJob();
            } else{
                handleNow();
            }
        }

        //Check if message contains a notification payload.
        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            String getMessage = remoteMessage.getNotification().getBody();
            if(TextUtils.isEmpty(getMessage)){
                Log.e(TAG, "ERR: Message data is empty...");
            } else  {
                Map<String, String> mapMessage = new HashMap<>();
                assert getMessage != null;
                mapMessage.put("key", getMessage);

                sendNotification(mapMessage, remoteMessage.getNotification().getTitle());


                //Broadcast Data Sending Test
                Intent intent = new Intent("alert_data");
                intent.putExtra("msg", getMessage);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            }
        }
    }

    private void scheduleJob(){
        /*OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(this).beginWith(work).enqueue();*/
    }

    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /*푸시 알림 정보를 받아서 앱에서 알림을 띄워준다.*/
    private void sendNotification(Map<String, String> data, String title){
        int noti_id = 1;
        String getMessage = "";

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        intent.putExtra("notification_id", 0);
        //Push로 받은 데이터를 그대로 다시 intent에 넣어준다.
        if(data != null && data.size() > 0){
            for(String key : data.keySet()){
                getMessage = data.get(key);
                intent.putExtra(key, getMessage);
            }
        }

        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)//Fcm Message Test!  //현재는 Body부분만 뜨고 Title부분은 안뜸. 이전에는 떴는데 시발
                .setContentText(getMessage)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //Notification 채널을 설정합니다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(noti_id, notificationBuilder.build());


    }
}