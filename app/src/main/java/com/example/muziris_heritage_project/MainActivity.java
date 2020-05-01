package com.example.muziris_heritage_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                   //         openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_sms:
                   //         openFragment(SmsFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_notifications:
                        //    openFragment(NotificationFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };
}
