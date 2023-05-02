package roppy.dq10.seraphysearcher;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class InstanceIdService extends FirebaseInstanceIdService {

    /**
     * ログ出力用
     */
    private static final String TAG = "InstanceIdService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Refreshed token: " + refreshedToken);

    }
}
