package roppy.dq10.seraphysearcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;
import java.util.Map;

/**
 * Created by roppy on 2017/05/05.
 */

public class MessagingService extends FirebaseMessagingService {
    private final String TAG ="MessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        SharedPreferences sharedPreferences = getSharedPreferences(PresentationUtil.PREFERENCE_NAME,
                Context.MODE_PRIVATE);

        try{
            // 通知設定
            Map<String, String> data = remoteMessage.getData();
            String targetKey = data.get("target");

            if(targetKey == null){
                return;
            }

            long detectTime = Long.parseLong(data.get("detect_time"));
            Log.i(TAG,"target = " + targetKey + " detectTime = " + detectTime);

            long currentTime = Calendar.getInstance().getTimeInMillis();


            for(int targetIndex = 0 ;targetIndex < TargetConfig.TARGET_CONFIG_ARRAY.length; targetIndex++){
                TargetConfig targetConfig = TargetConfig.TARGET_CONFIG_ARRAY[targetIndex];
                if(targetConfig.getKey().equals(targetKey)
                   ){
                    // 該当ターゲットの最終検知時刻から出現時間以上離れている場合、最終検知時刻を更新する。
                    if (currentTime >=
                            sharedPreferences.getLong(
                                    PresentationUtil.getKeyWithSuffix(
                                            PresentationUtil.PREFERENCE_LAST_DETECTION_KEY,
                                            targetConfig.getKey()),0)
                                    + targetConfig.getExpirationTime()) {

                        // 該当ターゲットの検知設定が有効で、
                        // 現在時刻が該当ターゲットのトークンンデータ内の検知時刻＋出現時間以内の場合に限り
                        // アラームを起動する。
                        if(sharedPreferences.getBoolean(
                                PresentationUtil.getKeyWithSuffix(PresentationUtil.PREFERENCE_VALID_KEY, targetConfig.getKey()), false)
                        && currentTime < detectTime + targetConfig.getExpirationTime()) {
                            notifyDetection(sharedPreferences, targetConfig, detectTime);
                        }
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putLong(
                                PresentationUtil.getKeyWithSuffix(PresentationUtil.PREFERENCE_LAST_DETECTION_KEY, targetConfig.getKey()), detectTime);
                        editor1.commit();
                    }
                    break;
                }
            }
        }catch (NumberFormatException nfe){
            Log.w(TAG,nfe.toString());
        }
    }

    private void notifyDetection(SharedPreferences sharedPreferences, TargetConfig targetConfig,long detectTime){
        PresentationUtil.notifyAppearance(this,
                targetConfig.getNotificationText(),
                sharedPreferences.getBoolean(PresentationUtil.PREFERENCE_VIBRATE_KEY,
                        false) );

        if(sharedPreferences.getBoolean(PresentationUtil.PREFERENCE_SOUND_KEY,
                false)) {

            PresentationUtil.playSound(this,
                    PresentationUtil.ALARM_DURATION_TIME_ARRAY[
                    sharedPreferences.getInt(PresentationUtil.PREFERENCE_DURATION_INDEX_KEY,
                            0)]);
        }

    }

}
