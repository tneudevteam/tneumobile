package com.mobile.tneu.tneumobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.mobile.tneu.tneumobile.Utils.AppDefaultPrefs;
import com.mobile.tneu.tneumobile.Utils.Injector;
import com.mobile.tneu.tneumobile.Utils.Logger;
import com.mobile.tneu.tneumobile.adapter.NewsRecyclerViewAdapter;
import com.mobile.tneu.tneumobile.model.News;
import com.mobile.tneu.tneumobile.presenter.NewsPresenter;
import com.mobile.tneu.tneumobile.service.NewsWatcherResponseReceiver;
import com.mobile.tneu.tneumobile.ui.NewsView;
import com.mobile.tneu.tneumobile.ui.activities.ClickListener;

import java.util.List;

public class HomeActivity extends MvpActivity<NewsView, NewsPresenter> implements ClickListener, NewsView, NavigationView.OnNavigationItemSelectedListener {
  private static final String LOG_TAG = Logger.getLogTag(HomeActivity.class);
  SwitchCompat switchCompat;
  private NewsRecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Injector.get().setContext(this);

    init();
    presenter.getNews();
  }

  @NonNull
  @Override
  public NewsPresenter createPresenter() {
    return new NewsPresenter();
  }

  private void init() {
    setContentView(R.layout.activity_home);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    switchCompat = (SwitchCompat) MenuItemCompat.getActionView(navigationView.getMenu().findItem(R.id.navi_item_create_notifications));
    initCheckBoxes();

    checkToSheduleAlarm();

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new NewsRecyclerViewAdapter(recyclerView);
    recyclerView.setAdapter(adapter);
    adapter.setOnLoadMoreListener(new NewsRecyclerViewAdapter.OnLoadMoreListener() {
      @Override
      public void onLoadMore() {
        presenter.getNews(adapter.getItemCount());
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    setCheckBoxes();
  }

  private void setCheckBoxes() {
    switchCompat.setChecked(checkSetiingsNotif());
  }

  private void initCheckBoxes() {
    setCheckBoxes();
    switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
  }

  private void checkToSheduleAlarm() {
    if (AppDefaultPrefs.getAppBoolean(AppDefaultPrefs.PREFS_SETTINGS_SHOW_NOTIF)) {
      NewsWatcherResponseReceiver.sheduleAlarm();
    }
  }

  private boolean checkSetiingsNotif() {
    return AppDefaultPrefs.getAppBoolean(AppDefaultPrefs.PREFS_SETTINGS_SHOW_NOTIF);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      Intent intent = new Intent(this, SettingsActivity.class);
      startActivity(intent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_map) {
      Intent intent = new Intent(this, MapActivity.class);
      startActivity(intent);
    } else if (id == R.id.nav_moduleok) {
      Intent intent = new Intent(this, ModuleokActivity.class);
      startActivity(intent);
    } else if (id == R.id.nav_qr_code) {
      Intent intent = new Intent(this, QRscannerActivity.class);
      startActivity(intent);
    } else if (id == R.id.nav_about) {
      showDialog();
    } else if (id == R.id.nav_shedule) {
      Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tneu.edu.ua/timetable/"));
      startActivity(browserIntent);
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void showDialog() {
    new MaterialDialog.Builder(this)
        .title(R.string.about_title)
        .content(R.string.about_content)
        .negativeText(R.string.about_negative_text)
        .autoDismiss(true)
        .show();
  }

  @Override
  public void onNewsReceived(List<News> news) {
    adapter.addAll(news);
  }

  @Override
  public void onClick(RecyclerView.ViewHolder holder, View view, ClickType clickType, int position) {

  }
}
