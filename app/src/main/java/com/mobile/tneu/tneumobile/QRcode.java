package com.mobile.tneu.tneumobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;

import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRcode extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Scaner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        //Do anything with result here :D
        Log.w("handleResult", result.getText());
        if(result.getText().toString().contains("http://") || result.getText().toString().contains("https://")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(result.getText().toString())));
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scan result");
            builder.setMessage(result.getText());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        //Resume scanning
        mScannerView.resumeCameraPreview(this);
    }

    @Override
    protected void onRestart() {

        Scaner();
        super.onRestart();
    }

    public void Scaner (){
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

}
