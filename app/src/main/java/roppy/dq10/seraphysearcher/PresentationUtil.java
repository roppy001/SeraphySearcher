package roppy.dq10.seraphysearcher;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class PresentationUtil {
	public static final String TAG = "PresentationUtil";

	public static final String PREFERENCE_NAME = "pref";
	public static final String PREFERENCE_SOUND_KEY = "SOUND";
	public static final String PREFERENCE_VIBRATE_KEY = "VIBRATE";
	public static final String PREFERENCE_VALID_KEY = "VALID";
	public static final String PREFERENCE_LAST_DETECTION_KEY = "LAST_DETECTION";
	public static final String PREFERENCE_DURATION_INDEX_KEY = "DURATION_INDEX";

	public static final String SERVICE_DURATION_KEY = "DURATION";

	public static final String CHANNEL_ID = "channel_id1";

	public static final String TEST_SENTENCE = "通知テストです";

	public static final long [] ALARM_DURATION_TIME_ARRAY = {30 * 60L * 1000L, 10 * 60L * 1000L, 3 * 60L * 1000L, 1 * 60L * 1000L, 12L * 1000L};

	public static void createChannel(Context context) {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationManager notificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);

			NotificationChannel channel = new NotificationChannel(
					// アプリでユニークな ID
					CHANNEL_ID,
					// ユーザが「設定」アプリで見ることになるチャンネル名
					context.getString(R.string.channel_name),
					// チャンネルの重要度
					NotificationManager.IMPORTANCE_DEFAULT
			);

			// 通知時のライトの色
			channel.setLightColor(Color.GREEN);
			// ロック画面で通知を表示するかどうか
			channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
			// 振動の初期値を有効にする
			channel.enableVibration(true);

			// 通知の音は消す
			channel.setSound(null, null);

			// 端末にチャンネルを登録し、「設定」で見れるようにする
			notificationManager.createNotificationChannel(channel);

		}
	}

	public static void notifyAppearance(Context context, String text,
			boolean vibrate) {
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent notificationIntent = new Intent(context, MainActivity.class);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT|PendingIntent.FLAG_MUTABLE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        	//現在の振動設定はOSに移譲する。

            Notification notification = new Notification.Builder(context, CHANNEL_ID)
                    // アイコン
                    .setSmallIcon(R.drawable.bell_noborder)
                    // 通知バーに表示する簡易メッセージ
                    .setTicker(text)
                    // 時間
                    .setWhen(System.currentTimeMillis())
                    // 展開メッセージのタイトル
                    .setContentTitle("出現通知")
                    // 展開メッセージの詳細メッセージ
                    .setContentText(text)
                    // PendingIntent
                    .setContentIntent(contentIntent)
                    // タップするとキャンセル
                    .setAutoCancel(true).build();

            notificationManager.notify(1, notification);
        } else {
			int def = 0;

			if (vibrate) {
				def |= Notification.DEFAULT_VIBRATE;
			}

			Notification notification = new NotificationCompat.Builder(context)
                    // アイコン
                    .setSmallIcon(R.drawable.bell_noborder)
                    // 通知バーに表示する簡易メッセージ
                    .setTicker(text)
                    // 時間
                    .setWhen(System.currentTimeMillis())
                    // 展開メッセージのタイトル
                    .setContentTitle("出現通知")
                    // 展開メッセージの詳細メッセージ
                    .setContentText(text)
                    // PendingIntent
                    .setContentIntent(contentIntent)
                    // 通知時の音・バイブ・ライト
                    .setDefaults(def)
                    // タップするとキャンセル
                    .setAutoCancel(true).build();

    		notificationManager.notify(1, notification);
        }
	}

	public static String getKeyWithSuffix(String str, String suffix) {
		return str + "_" + suffix;
	}

	public static void playSound(Context context,long duration) {
		context.startService(new Intent(context, SoundPlayerService.class).putExtra(SERVICE_DURATION_KEY,duration));
	}

	public static void stopSound(Context context) {
		context.stopService(new Intent(context, SoundPlayerService.class));
	}
}
