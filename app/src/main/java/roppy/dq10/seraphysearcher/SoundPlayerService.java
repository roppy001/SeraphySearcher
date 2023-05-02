package roppy.dq10.seraphysearcher;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SoundPlayerService extends Service {
	private MediaPlayer mediaPlayer;
	private static long DEFAULT_DURATION = 30 * 60L * 1000L;

	@Override
	public void onCreate() {
		super.onCreate();

		mediaPlayer = MediaPlayer.create(this, R.raw.alertsound);
		mediaPlayer.setLooping(true);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
	    if(!mediaPlayer.isPlaying()) {
	    	mediaPlayer.start();
	    }

	    new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				stopSelf();
			}
		}, intent.getLongExtra(PresentationUtil.SERVICE_DURATION_KEY,DEFAULT_DURATION));
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		if(mediaPlayer != null){
		    if(mediaPlayer.isPlaying()) {
		    	mediaPlayer.stop();
		    }
			mediaPlayer.release();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
