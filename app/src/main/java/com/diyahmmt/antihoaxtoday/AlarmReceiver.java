package com.diyahmmt.antihoaxtoday;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String ALARMONETIME = "AlarmOneTime";
    public static final String ALARMREPEATING = "AlarmRepeating";
    public static final String MESSAGES = "messages";
    public static final String TYPES = "types";

    private final int IDONETIME = 100;
    private final int IDREPEATING = 101;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(TYPES);
        String message = intent.getStringExtra(MESSAGES);

        String title = type.equalsIgnoreCase(ALARMONETIME) ? "One Time Alarm" : "Repeating Alarm";
        int notifId = type.equalsIgnoreCase(ALARMONETIME) ? IDONETIME : IDREPEATING;

        title = context.getResources().getString(R.string.app_name);
        showAlarmNotification(context, title, message, notifId);
    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        notificationManagerCompat.notify(notifId, builder.build());
    }

    public void setRepeatingAlarm(Context ctx, String ty, String time, String msg) {
        AlarmManager service = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        Intent in = new Intent(ctx, AlarmReceiver.class);
        in.putExtra(MESSAGES, msg);
        in.putExtra(TYPES, ty);

        String arrayTime[] = time.split(":");

        Calendar calendarTime = Calendar.getInstance();
        calendarTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayTime[0]));
        calendarTime.set(Calendar.MINUTE, Integer.parseInt(arrayTime[1]));
        calendarTime.set(Calendar.SECOND, 0);

        if (calendarTime.before(Calendar.getInstance())) calendarTime.add(Calendar.DATE, 1);

        int requestCode = IDREPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, requestCode, in, PendingIntent.FLAG_UPDATE_CURRENT);

        service.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendarTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
