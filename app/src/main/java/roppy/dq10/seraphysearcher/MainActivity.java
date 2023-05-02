package roppy.dq10.seraphysearcher;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i(TAG, "Launching Time (Long) = " + Calendar.getInstance().getTimeInMillis());

		sharedPreferences = getSharedPreferences(PresentationUtil.PREFERENCE_NAME,
				Context.MODE_PRIVATE);

		//ListViewを構築する
        ListView listView = (ListView)findViewById(R.id.listViewTarget);

        //ListViewで表示するオブジェクトの生成
        ListViewData listViewData = new ListViewData();

        //チェック状態を設定する。
        listViewData.setSound(sharedPreferences.getBoolean(PresentationUtil.PREFERENCE_SOUND_KEY, false));
        listViewData.setVibrate(sharedPreferences.getBoolean(PresentationUtil.PREFERENCE_VIBRATE_KEY, false));
		listViewData.setDurationIndex(sharedPreferences.getInt(PresentationUtil.PREFERENCE_DURATION_INDEX_KEY,0));

        //ターゲットリストデータを生成する
        List<TargetListData> targetList = new ArrayList<TargetListData>();

        for(TargetConfig targetConfig : TargetConfig.TARGET_CONFIG_ARRAY){
        	TargetListData target = new TargetListData();
        	target.setKey(targetConfig.getKey());
        	target.setName(targetConfig.getTargetName());
			target.setTimeDescription("");
			target.setColorType(TargetListData.COLOR_NORMAL_TYPE);
			target.setQueryString(targetConfig.getQuery());
        	target.setChecked(sharedPreferences.getBoolean(
					PresentationUtil.getKeyWithSuffix(PresentationUtil.PREFERENCE_VALID_KEY, targetConfig.getKey()), false) );

        	targetList.add(target);
        }
        listViewData.setTargetList(targetList);

        //リスト画面データをアダプタに設定し、ListViewに設定する
        MainListAdapter adapter = new MainListAdapter(this, sharedPreferences, listViewData);
        listView.setAdapter(adapter);

		//全受信トピックを作成
		FirebaseMessaging.getInstance().subscribeToTopic("/topics/all-2.0");

		//チャネルを登録する(android 8.0以降のみ有効)
		PresentationUtil.createChannel(this);


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		displayLastDetectionDate();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	public void onClickStopSound(View view) {
		PresentationUtil.stopSound(this);
	}

	public void displayLastDetectionDate(){
        ListView listView = (ListView)findViewById(R.id.listViewTarget);
		MainListAdapter adapter = (MainListAdapter)listView.getAdapter();
		List<TargetListData> targetList = adapter.getListViewData().getTargetList();

		long currentTime = Calendar.getInstance().getTimeInMillis();

		for(int i = 0 ;i < TargetConfig.TARGET_CONFIG_ARRAY.length ; i++){
			long detectTime = sharedPreferences.getLong(
					PresentationUtil.getKeyWithSuffix(
							PresentationUtil.PREFERENCE_LAST_DETECTION_KEY,
							TargetConfig.TARGET_CONFIG_ARRAY[i].getKey()),0);

			TargetListData tld =targetList.get(i);
			tld.setTimeDescription(
					getTimeDescription(currentTime, detectTime));

			tld.setColorType(currentTime - detectTime < TargetConfig.TARGET_CONFIG_ARRAY[i].getExpirationTime() ?
			TargetListData.COLOR_ALERT_TYPE : TargetListData.COLOR_NORMAL_TYPE);
		}

		adapter.notifyDataSetChanged();
	}

	/**
	 * 時刻差から、時刻差を表す文字列を返却する。
	 * @return 時刻差を表す文字列
	 */
	protected String getTimeDescription(Long current, Long target){
		if(target == 0) {
			return "??";
		}else {
			Long diff = current - target;
			if(diff < 60L * 1000L){
				return (diff / 1000L) + "s";
			}else if(diff < 60L * 60L * 1000L){
				return (diff / 60L / 1000L) + "m";
			}else if(diff < 24L * 60L * 60L * 1000L){
				return (diff / 60L / 60L / 1000L) + "h";
			}else if(diff < 7L * 24L * 60L * 60L * 1000L){
				return (diff / 24L / 60L / 60L / 1000L) + "d";
			}else {
				return "1w";
			}
		}

	}

}
