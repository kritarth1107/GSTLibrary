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

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    GridLayout G1,G2;
    Toolbar toolbar;
    ImageView arrowImg,arrowImg2;
    private DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    CardView searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                loadFragment(new HomeFragment());
                                toolbar.setBackgroundResource(R.drawable.gradbg);
                                break;
                            case R.id.news_bottom_navigation:
                                break;
                            case R.id.scan_bottom_navigation:
                                break;
                            case R.id.wallet_bottom_navigation:
                                loadFragment(new ProfileFragment());
                                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

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


    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayoutMain, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
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
