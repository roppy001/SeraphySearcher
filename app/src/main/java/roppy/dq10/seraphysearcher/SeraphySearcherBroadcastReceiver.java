package roppy.dq10.seraphysearcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SeraphySearcherBroadcastReceiver extends BroadcastReceiver {

	/**
	 * ブロードキャスト受信時の動作
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// 他のアプリ更新時は対象外とする
		if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
			if (!intent.getDataString().equals(
					"package:" + context.getPackageName())) {
				return;
			}
		}

		// AlarmManager を開始する
		// SeraphySearcherService.startAlarm(context);
	}
}
