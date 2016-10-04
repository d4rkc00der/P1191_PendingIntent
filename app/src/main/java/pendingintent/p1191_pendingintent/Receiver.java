package pendingintent.p1191_pendingintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by user on 04.10.2016.
 */

public class Receiver extends BroadcastReceiver{

    final String LOG_TAG ="MyLog";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG,"onReceive");
        Log.d(LOG_TAG,"action = " + intent.getAction());
        Log.d(LOG_TAG,"extra = " + intent.getStringExtra("extra"));
    }
}
