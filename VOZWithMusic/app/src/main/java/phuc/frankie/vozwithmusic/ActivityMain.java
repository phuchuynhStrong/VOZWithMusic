package phuc.frankie.vozwithmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import phuc.frankie.vozwithmusic.Database.Database;
import phuc.frankie.vozwithmusic.Utility.Utility;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Database db;
    EditText mSearchEdt;
    FragmentMain mainFragment;
    ImageButton mSettingBtn;
    AppCompatActivity appCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCompat = this;
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainFragment = new FragmentMain();
        getSupportFragmentManager().beginTransaction().add(R.id.main_container,mainFragment).commit();

        db = Database.getInstance();
        mSearchEdt = (EditText) findViewById(R.id.search_input);
        mSettingBtn = (ImageButton) findViewById(R.id.setting_btn);
        mSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),ActivitySettings.class));
                appCompat.overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });
        setUpText();

    }

    private void setUpText() {
        Subscription _mSubscription = RxTextView.textChangeEvents(mSearchEdt)//
                .skip(1)
                .debounce(500, TimeUnit.MILLISECONDS)// default Scheduler is Computation
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(getdata());
    }

    public Observer<TextViewTextChangeEvent> getdata()
    {

        return new Observer<TextViewTextChangeEvent>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                mainFragment.filterByName(onTextChangeEvent.text().toString());
            }
        };
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_story) {
            mainFragment.filterByType(Utility.TYPE_REVIEW);
            // Handle the camera action
        } else if (id == R.id.nav_novel) {
            mainFragment.filterByType(Utility.TYPE_NOVEL);
        } else if (id == R.id.nav_diary) {
            mainFragment.filterByType(Utility.TYPE_DIARY);
        } else if (id == R.id.nav_all) {
            mainFragment.filterByType(Utility.TYPE_ALL);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
