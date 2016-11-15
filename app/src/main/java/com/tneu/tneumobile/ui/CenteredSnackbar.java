package com.tneu.tneumobile.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by stepanv on 29.09.16.
 */

public class CenteredSnackbar {

  public static Snackbar make(View view, String text, int duration) {
    Snackbar snackbar = Snackbar.make(view, text, duration);
    View snackbarView = snackbar.getView();
    ViewGroup.LayoutParams params = snackbarView.getLayoutParams();

    if (params instanceof FrameLayout.LayoutParams) {
      ((FrameLayout.LayoutParams) params).gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    } else if (params instanceof LinearLayout.LayoutParams) {
      ((LinearLayout.LayoutParams) params).gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    } else if (params instanceof CoordinatorLayout.LayoutParams) {
      ((CoordinatorLayout.LayoutParams) params).gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    } else if (params instanceof RelativeLayout.LayoutParams) {
      ((RelativeLayout.LayoutParams) params).addRule(RelativeLayout.CENTER_HORIZONTAL);
      ((RelativeLayout.LayoutParams) params).addRule(RelativeLayout.ALIGN_BOTTOM);
    }

    snackbarView.setLayoutParams(params);
    return snackbar;
  }
}
