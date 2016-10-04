package pendingintent.p1191_pendingintent;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final String LOG_TAG = "MyLog";

    NotificationManager nm;
    AlarmManager am;
    Intent intent1;
    Intent intent2;

    PendingIntent pIntent1;
    PendingIntent pIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        am = (AlarmManager)getSystemService(ALARM_SERVICE);
    }

    public void onClick1(View view) {
        intent1 = createIntent("action 1","extra 1");
        pIntent1 = PendingIntent.getBroadcast(this,0,intent1,0);

        intent2 = createIntent("action 2", "extra 2");
        pIntent2 = PendingIntent.getBroadcast(this,0,intent2,0);
        compare();
        sendNotif(1,pIntent1);
        sendNotif(2,pIntent2);
    }

    public void onClick2(View view) {

    }

    Intent createIntent(String action, String extra) {
        Intent intent = new Intent(this,Receiver.class);
        intent.setAction(action);
        intent.putExtra("extra",extra);
        return intent;
    }

    void compare() {
        Log.d(LOG_TAG, "intent1 = intent2: " + intent1.filterEquals(intent2));
        Log.d(LOG_TAG, "pIntent1 = pIntent2: " + pIntent1.equals(pIntent2));
    }

    void sendNotif(int id, PendingIntent pIntent) {
        Notification.Builder builder = new Notification.Builder(MainActivity.this);

        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentTitle("Id = " + id);
        builder.setContentText("You have a new message");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pIntent);
        builder.setOngoing(true);
        //API level 16
        builder.setNumber(100);
        builder.build();
        nm.notify(id,builder.getNotification());
    }
}
