package com.mobile.tneu.tneumobile.ui.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by stepanv on 28.09.16.
 */

public interface ClickListener {
  enum ClickType {
    SIMPLE,
    LONG,
    DOUBLE
  }

  void onClick(RecyclerView.ViewHolder holder, View view, ClickType clickType, int position);
}
