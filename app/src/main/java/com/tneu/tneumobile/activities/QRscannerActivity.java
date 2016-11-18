package com.tneu.tneumobile.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;
import com.tneu.tneumobile.R;
import com.tneu.tneumobile.Utils.Logger;
import com.tneu.tneumobile.Utils.PermissionUtil;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRscannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
  // TODO: Make it MVP
  public static final int REQUEST_PERMISSION_CAMERA = 1;
  private static final String LOG_TAG = Logger.getLogTag(QRscannerActivity.class);
  private ZXingScannerView mScannerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_qr);
    if (checkCameraPermission()) {
      scan();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (mScannerView != null) {
      mScannerView.stopCamera();
    }
  }

  @Override
  public void handleResult(Result result) {
    //Do anything with result here :D
    Logger.d("handleResult", result.getText());
    if (result.getText().contains("http://") || result.getText().contains("https://")) {
      startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(result.getText())));
    } else {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle("scan result");
      builder.setMessage(result.getText());
      AlertDialog alertDialog = builder.create();
      alertDialog.show();
    }
    //Resume scanning
    mScannerView.resumeCameraPreview(this);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {
      case REQUEST_PERMISSION_CAMERA:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          scan();
        } else {
          PermissionUtil.showSnackbarForCameraPermission(this, findViewById(R.id.qr_relative));
        }
    }
  }

  @Override
  protected void onRestart() {
    scan();
    super.onRestart();
  }

  public void scan() {
    mScannerView = new ZXingScannerView(this);
    setContentView(mScannerView);
    mScannerView.setResultHandler(this);
    mScannerView.startCamera();
  }

  private boolean checkCameraPermission() {
    return PermissionUtil.checkCameraPermission(this, findViewById(R.id.qr_relative), REQUEST_PERMISSION_CAMERA);
  }

}
