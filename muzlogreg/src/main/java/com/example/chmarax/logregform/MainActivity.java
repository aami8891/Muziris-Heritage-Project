package com.example.chmarax.logregform;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private Button btnLogout;
    private FirebaseAuth firebaseAuth;
    private Fragment mFragment = null;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_navigation);
       // btnLogout = findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        mFragment = new SelectionFragment();
        fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                firebaseAuth.signOut();
//                finish();
//                startActivity(new Intent(MainActivity.this,LoginActivity.class));
//            }
//        });
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            mFragment = new HomeFragment();
                            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
                            //         openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_sms:
                            mFragment = new MapFragment();
                            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
                            //         openFragment(SmsFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_notifications:
                            //    openFragment(NotificationFragment.newInstance("", ""));
                         //   Toast.makeText(getApplicationContext(),"here ",Toast.LENGTH_LONG).show();
                            mFragment = new SelectionFragment();
                            fragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
                            return true;
                    }
                    return false;
                }
            };
}
