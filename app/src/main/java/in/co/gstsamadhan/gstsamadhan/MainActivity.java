package in.co.gstsamadhan.gstsamadhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

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
                                startActivity(new Intent(MainActivity.this,NewsActivity.class));

                                break;
                            case R.id.scan_bottom_navigation:

                                break;
                            case R.id.wallet_bottom_navigation:
                                if (frag.equals("Wallet")){

                                }
                                else{
                                    frag="Wallet";
                                    loadFragment(new ProfileFragment());
                                }


                                break;

                        }
                        return true;
                    }
                });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                startActivity(new Intent(MainActivity.this,LoginActivity.class));

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.FrameLayoutMain, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                sessionManager.logout();
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

                super.onBackPressed();


        }
    }

}
