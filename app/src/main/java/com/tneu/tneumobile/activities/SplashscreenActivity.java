package com.tneu.tneumobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;

import com.tneu.tneumobile.R;

/**
 * Created by stepanv on 18.10.16.
 */

public class SplashscreenActivity extends Activity {
  /**
   * Called when the activity is first created.
   */
  Thread splashTread;

  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    Window window = getWindow();
    window.setFormat(PixelFormat.RGBA_8888);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splashscreen);
    StartAnimations();
  }

  private void StartAnimations() {
    splashTread = new Thread() {
      @Override
      public void run() {
        try {
          int waited = 0;
          // Splash screen pause time
          while (waited < 1500) {
            sleep(100);
            waited += 100;
          }
          Intent intent = new Intent(SplashscreenActivity.this,
              HomeActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
          startActivity(intent);
          SplashscreenActivity.this.finish();
        } catch (InterruptedException e) {
          // do nothing
        } finally {
          SplashscreenActivity.this.finish();
        }

      }
    };
    splashTread.start();

  }

}
