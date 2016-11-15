package com.tneu.tneumobile.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.tneu.tneumobile.R;
import com.tneu.tneumobile.activities.QRscannerActivity;
import com.tneu.tneumobile.ui.CenteredSnackbar;

/**
 * Created by stepanv on 29.09.16.
 */

public class PermissionUtil {
  public static boolean checkCameraPermission(Activity activity, View view, int requestCode) {
    return checkOrAksCameraPermission(activity, view, requestCode, Manifest.permission.CAMERA, activity.getString(R.string.deny_camera));
  }

  public static void showSnackbarForCameraPermission(Activity activity, View view) {
    askPermission(activity, view, QRscannerActivity.REQUEST_PERMISSION_CAMERA, Manifest.permission.CAMERA, activity.getString(R.string.deny_camera));
  }

  private static boolean checkOrAksCameraPermission(Activity activity, View view, int requestCode, final String permission, String message) {
    if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
        askPermission(activity, view, requestCode, permission, message);
      } else {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
      }
      return false;
    }
    return true;
  }

  public static void askPermission(@NonNull final Activity activity, View view, final int requestCode, final String permission, String message) {
    final boolean isShouldShowRequest = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    CenteredSnackbar.make(
        view,
        message,
        Snackbar.LENGTH_LONG
    ).setAction(
        isShouldShowRequest ?
            activity.getString(R.string.permission_action_grant) :
            activity.getString(R.string.permission_action_setting),
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (isShouldShowRequest) {
              ActivityCompat.requestPermissions(
                  activity,
                  new String[]{permission},
                  requestCode
              );
            } else {
              activity.startActivity(new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));
            }
          }
        }
    ).show();
  }
}
