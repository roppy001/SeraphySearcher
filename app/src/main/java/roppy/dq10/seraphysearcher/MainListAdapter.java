package roppy.dq10.seraphysearcher;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainListAdapter extends BaseAdapter {
	private static final String TAG = "MainListAdapter";
	private MainActivity context;
    private LayoutInflater layoutInflater = null;
	private SharedPreferences sharedPreferences;
	private ListViewData listViewData;

	private static final int COLOR_ON = Color.rgb(0x00, 0x00, 0x00);
	private static final int COLOR_OFF = Color.rgb(0x90, 0x90, 0x90);
	private static final int COLOR_TIME_NORMAL = Color.rgb(0x90, 0x90, 0x90);
	private static final int COLOR_TIME_ALERT = Color.rgb(0xFF, 0x00, 0x30);

	//行インデックス値を保持
	private int count;
	private int generalHeaderIndex;
	private int soundIndex;
	private int alarmDurationIndex;
	private int vibrateIndex;
	private int notificationConfigIndex;
	private int resetIndex;
	private int testIndex;
	private int targetHeaderIndex;
	private int targetIndex;

	public MainListAdapter(MainActivity context, SharedPreferences sharedPreferences, ListViewData listViewData) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        this.listViewData = listViewData;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        calculateRowIndices();
	}

	public void calculateRowIndices() {
		count = 0;

		generalHeaderIndex = count++;
		soundIndex = count++;

		alarmDurationIndex = count++;

		// Android 8.0以降では、振動設定は設定画面に遷移させて行う
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			vibrateIndex = -1;
			notificationConfigIndex = count++;
		} else {
			vibrateIndex = count++;
			notificationConfigIndex = -1;
		}

		resetIndex = count++;
		testIndex = count++;
		targetHeaderIndex = count++;
		targetIndex = count;
		count += listViewData.getTargetList().size();
	}

	public ListViewData getListViewData() {
		return listViewData;
	}
	public void setListViewData(ListViewData listViewData) {
		this.listViewData = listViewData;
		calculateRowIndices();
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (position == generalHeaderIndex) {
	        convertView = layoutInflater.inflate(R.layout.general_header, parent, false);

		}else if (position == soundIndex){
	        convertView = layoutInflater.inflate(R.layout.sound_row, parent, false);

			OnClickListener listener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					listViewData.setSound(!listViewData.isSound());

					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putBoolean(PresentationUtil.PREFERENCE_SOUND_KEY, listViewData.isSound());
					editor.commit();
					notifyDataSetChanged();
				}
			};

			TextView tv = ((TextView)convertView.findViewById(R.id.textViewSound));
			tv.setText(listViewData.isSound() ? R.string.config_sound_label_on : R.string.config_sound_label_off);
			tv.setTextColor(listViewData.isSound() ? COLOR_ON : COLOR_OFF);
			tv.setOnClickListener(listener);

	        CheckBox checkSound = (CheckBox)convertView.findViewById(R.id.checkBoxSound);
	        checkSound.setChecked(listViewData.isSound());
	        checkSound.setOnClickListener(listener);


		}else if (position == alarmDurationIndex){
			convertView = layoutInflater.inflate(R.layout.alarm_duration_row, parent, false);
			Spinner spinner = (Spinner)convertView.findViewById(R.id.spinnerAlarmDuration);
			spinner.setSelection(listViewData.getDurationIndex(),false);

			spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					listViewData.setDurationIndex(position);

					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putInt(PresentationUtil.PREFERENCE_DURATION_INDEX_KEY, listViewData.getDurationIndex());
					editor.commit();
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					listViewData.setDurationIndex(0);

					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putInt(PresentationUtil.PREFERENCE_DURATION_INDEX_KEY, listViewData.getDurationIndex());
					editor.commit();
				}
			});

		}else if (position == vibrateIndex){
			convertView = layoutInflater.inflate(R.layout.vibrate_row, parent, false);

			OnClickListener listener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					listViewData.setVibrate(!listViewData.isVibrate());

					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putBoolean(PresentationUtil.PREFERENCE_VIBRATE_KEY, listViewData.isVibrate());
					editor.commit();
					notifyDataSetChanged();
				}
			};

			TextView tv = ((TextView)convertView.findViewById(R.id.textViewVibrate));
			tv.setText(listViewData.isVibrate() ? R.string.config_vibrate_label_on : R.string.config_vibrate_label_off);
			tv.setTextColor(listViewData.isVibrate() ? COLOR_ON : COLOR_OFF);
			tv.setOnClickListener(listener);

			CheckBox checkVibrate = (CheckBox)convertView.findViewById(R.id.checkBoxVibrate);

			checkVibrate.setChecked(listViewData.isVibrate());
			checkVibrate.setOnClickListener(listener);

		}else if (position == notificationConfigIndex){
			convertView = layoutInflater.inflate(R.layout.notification_config_row, parent, false);
			Button button = (Button)convertView.findViewById(R.id.buttonNotificationConfig);

			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent (Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
					intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
					intent.putExtra(Settings.EXTRA_CHANNEL_ID, PresentationUtil.CHANNEL_ID);
					context.startActivity(intent);
				}
			});

		}else if (position == resetIndex){
	        convertView = layoutInflater.inflate(R.layout.reset_row, parent, false);
	        Button buttonReset = (Button)convertView.findViewById(R.id.buttonReset);
	        buttonReset.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					SharedPreferences.Editor editor = sharedPreferences.edit();
					for(TargetConfig targetConfig : TargetConfig.TARGET_CONFIG_ARRAY) {
						editor.remove(PresentationUtil.getKeyWithSuffix(PresentationUtil.PREFERENCE_LAST_DETECTION_KEY, targetConfig.getKey()));
					}
					editor.commit();
					context.displayLastDetectionDate();
				}
			});


		}else if (position == testIndex){
	        convertView = layoutInflater.inflate(R.layout.test_row, parent, false);

	        Button buttonReset = (Button)convertView.findViewById(R.id.buttonTest);
	        buttonReset.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					PresentationUtil.notifyAppearance(context,
							PresentationUtil.TEST_SENTENCE,
							sharedPreferences.getBoolean(PresentationUtil.PREFERENCE_VIBRATE_KEY, false));

					if(sharedPreferences.getBoolean(PresentationUtil.PREFERENCE_SOUND_KEY,
							false)) {
						PresentationUtil.playSound(context,
								PresentationUtil.ALARM_DURATION_TIME_ARRAY[listViewData.getDurationIndex()]);
					}
				}
			});
		}else if (position == targetHeaderIndex){
	        convertView = layoutInflater.inflate(R.layout.target_header_row, parent, false);
		}else // if (position >= targetIndex && position < count)
		{
	        convertView = layoutInflater.inflate(R.layout.target_row, parent, false);
			final int listIndex = position - targetIndex;
			final TargetListData target = listViewData.getTargetList().get(listIndex);


	        TextView tv = ((TextView)convertView.findViewById(R.id.textViewTarget));
			tv.setText(target.getName());
			tv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String uriString = getUriString(target.getQueryString());
					if(uriString != null && !"".equals(uriString)) {
						Uri uri = Uri.parse(uriString);
						Intent it = new Intent(Intent.ACTION_VIEW, uri);
						context.startActivity(it);
					}
				}
			});
			if (target.getColorType() == TargetListData.COLOR_ALERT_TYPE) {
				tv.setTextColor(COLOR_TIME_ALERT);
			}

			TextView tv2 = ((TextView)convertView.findViewById(R.id.textViewTimeDescription));
			tv2.setText(target.getTimeDescription());

			if (target.getColorType() == TargetListData.COLOR_ALERT_TYPE) {
				tv2.setTextColor(COLOR_TIME_ALERT);
			} else {
				tv2.setTextColor(COLOR_TIME_NORMAL);
			}

	        Switch s1 = (Switch)convertView.findViewById(R.id.switchTarget);
	        s1.setChecked(target.isChecked());
	        s1.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	boolean b = ((Switch)v).isChecked();
	            	TargetListData t = listViewData.getTargetList().get(listIndex);
	            	t.setChecked(b);

	        		SharedPreferences.Editor editor = sharedPreferences.edit();
	        		editor.putBoolean(PresentationUtil.getKeyWithSuffix(
	        				PresentationUtil.PREFERENCE_VALID_KEY,
	        				t.getKey()), b);
	        		editor.commit();
	        	}
	        });
		}

        return convertView;
	}

	private static String getUriString(String queryString) {
		String s = "";
		try {
		  s = "https://twitter.com/search?f=tweets&vertical=default&q="+
				URLEncoder.encode(queryString,"UTF-8")+"&src=typd&lang=ja";
		}catch (UnsupportedEncodingException e){
		}
		return s;
	}

}
