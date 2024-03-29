package in.co.gstsamadhan.gstsamadhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import in.co.gstsamadhan.gstsamadhan.Login.LoginActivity;
import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    GridLayout G1,G2;
    Toolbar toolbar;
    ImageView arrowImg,arrowImg2;
    private DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    CardView searchbar,LoginReg;
    String frag="Home";
    SessionManager sessionManager;
    TextView UserIdTV,UserEmailTV,UserMobileTV,UserNameTV;
    LinearLayout UserDetLY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);   //SessionManager Declare
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchbar = findViewById(R.id.searchbar);

        frameLayout = findViewById(R.id.FrameLayoutMain);
        drawer = findViewById(R.id.drawer_layout);
        bottomNavigationView=findViewById(R.id.BottomNV);
        loadFragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home_bottom_navigation:
                                if(frag.equals("Home")){

                                }
                                else{
                                    frag="Home";
                                    loadFragment(new HomeFragment());
                                }

                                break;
                            case R.id.news_bottom_navigation:
                                Intent intent =new  Intent(MainActivity.this,NewsActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                overridePendingTransition(0,0);

                                break;
                            case R.id.scan_bottom_navigation: /*
                                startActivity(new Intent(MainActivity.this,ScanActivity.class));
                                overridePendingTransition(R.anim.fade_up_down,R.anim.fade_down_up);
                                */

                                startActivity(new Intent(MainActivity.this,ComingSoon.class));
                                break;
                            case R.id.wallet_bottom_navigation:/*
                                if (frag.equals("Wallet")){

                                }
                                else{
                                    frag="Wallet";
                                    loadFragment(new ProfileFragment());
                                } */
                                startActivity(new Intent(MainActivity.this,ComingSoon.class));


                                break;

                        }
                        return true;
                    }
                });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //    startActivity(new Intent(MainActivity.this,AdvancedSearch.class));
                startActivity(new Intent(MainActivity.this,ComingSoon.class));
            }
        });

        View headerView = navigationView.getHeaderView(0);
        LoginReg = headerView.findViewById(R.id.LoginRegButton);
        UserNameTV = headerView.findViewById(R.id.UserNameTV);
        UserEmailTV = headerView.findViewById(R.id.UserEmailTV);
        UserMobileTV = headerView.findViewById(R.id.UserMobileTV);
        UserDetLY = headerView.findViewById(R.id.UserDetLY);
        if(sessionManager.isLoggin()) {
            HashMap<String, String> user = sessionManager.getUserDetail();

            String mCLientID = user.get(sessionManager.CLIENT_ID);
            String mCLientName = user.get(sessionManager.CLIENT_NAME);
            String mCLientEmail = user.get(sessionManager.CLIENT_EMAIL);
            String mCLientMobile = user.get(sessionManager.CLIENT_MOBILE);

            LoginReg.setVisibility(View.GONE);
            UserEmailTV.setText(mCLientEmail);
            UserMobileTV.setText("+91 "+mCLientMobile);
            UserNameTV.setText(mCLientName);
            UserDetLY.setVisibility(View.VISIBLE);
            UserDetLY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                }
            });
        }
        LoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.FrameLayoutMain, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                sessionManager.logout();
                break;

            case R.id.nav_profile:
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                break;
            case R.id.nav_pp:
                startActivity(new Intent(MainActivity.this,PrivacyPolicy.class));
                break;
            case R.id.nav_tou:
                customeTab("https://gstsamadhan.co.in/terms");
                break;
            case R.id.nav_contact:
                startActivity(new Intent(MainActivity.this,ContactUs.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this,ContactUs.class));
                break;
            case R.id.nav_support:
                startActivity(new Intent(MainActivity.this,ContactUs.class));
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);

        }
        else {
            if(frag.equals("Home")){
                super.onBackPressed();
            }
            else {
                frag="Home";
                bottomNavigationView.setSelectedItemId(R.id.home_bottom_navigation);
                loadFragment(new HomeFragment());
            }




        }
    }
    public void customeTab(String URL) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
        builder.addDefaultShareMenuItem();

        CustomTabsIntent anotherCustomTab = new CustomTabsIntent.Builder().build();


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_gst_samadhan_app_favicon);
        int requestCode = 100;
        Intent intent = anotherCustomTab.intent;
        intent.setData(Uri.parse(URL));

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setActionButton(bitmap, "Android", pendingIntent, true);
        builder.setShowTitle(true);


        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(MainActivity.this, Uri.parse(URL));
    }


}
