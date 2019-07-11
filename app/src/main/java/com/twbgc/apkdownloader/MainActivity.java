package com.twbgc.apkdownloader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // check the ACCESSIBILITY permission is on.
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.i("AccessibilityUtil", e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AccessibilityService Permission check
        if (!isAccessibilitySettingsOn(MainActivity.this)) {
            // direct to accessibilty settings
            startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), 1);
        } else {
            Toast.makeText(MainActivity.this, "ACCESSIBILITY Permission Already.", Toast.LENGTH_SHORT).show();
        }
    }

}
