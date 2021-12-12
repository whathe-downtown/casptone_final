package org.techtown.capstone_final;

import android.util.Log;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class FirebaseInstanceIDService extends FirebaseMessagingService{

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onNewToken(@NonNull @NotNull String token) {
        Log.d(TAG, "Refreshed token: " + token);

        /* TODO: 이후 생성등록된 토큰을 서버에 보내 저장해 두었다가 추가 작업을 할 수 있도록 한다.*/
        sendRegistrationToServer(token);
    }

    /*새로운 토큰이 생성되는 경우*/
    private void sendRegistrationToServer(String token) {
        Log.e(TAG, "here ! sendRegistrationToServer! token is " + token);

        /*FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {

            }
        });*/
    }
}