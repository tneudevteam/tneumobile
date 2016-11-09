package com.mobile.tneu.tneumobile.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.mobile.tneu.tneumobile.R;
import com.mobile.tneu.tneumobile.Utils.AppDefaultPrefs;
import com.mobile.tneu.tneumobile.service.NewsWatcherResponseReceiver;

/**
 * Created by stepanv on 18.10.16.
 */

public class SettingsActivity extends AppCompatActivity {
  CheckBox checkNotification;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    checkNotification = (CheckBox) findViewById(R.id.check_notifications);
    checkNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          AppDefaultPrefs.remove(AppDefaultPrefs.PREFS_SETTINGS_SHOW_NOTIF);
          NewsWatcherResponseReceiver.sheduleAlarm();
        } else {
          AppDefaultPrefs.putAppBoolean(AppDefaultPrefs.PREFS_SETTINGS_SHOW_NOTIF, false);
          NewsWatcherResponseReceiver.stopAlarm();
        }
      }
    });

    setCheckboxes();
  }

  private void setCheckboxes() {
    if (AppDefaultPrefs.getAppBoolean(AppDefaultPrefs.PREFS_SETTINGS_SHOW_NOTIF)) {
      checkNotification.setChecked(true);
    }
  }

}
