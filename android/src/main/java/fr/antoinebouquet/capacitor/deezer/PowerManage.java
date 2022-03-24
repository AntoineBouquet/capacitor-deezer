package fr.antoinebouquet.capacitor.deezer;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import static androidx.core.content.ContextCompat.startActivity;

public class PowerManage extends AppWidgetProvider {

    PowerManager pm;
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    private static final int IGNORE_BATTERY_OPTIMIZATION_REQUEST = 1002;

    private void askIgnoreOptimization(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getApplicationContext().getPackageName()));
            startActivity(context, intent, null);
        }
    }



    void activateLowBAttery(Context context){
        pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (pm != null && !pm.isIgnoringBatteryOptimizations(context.getApplicationContext().getPackageName())) {
                askIgnoreOptimization(context);
            } else {
                // already ignoring battery optimization code here next you want to do
            }
        } else {
            // already ignoring battery optimization code here next you want to do
        }
    }
}
