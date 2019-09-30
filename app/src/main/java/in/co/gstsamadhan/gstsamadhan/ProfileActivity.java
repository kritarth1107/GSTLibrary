package in.co.gstsamadhan.gstsamadhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout logout;
    SessionManager sessionManager;
    TextView name,email,phone,profession,city,plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = findViewById(R.id.LogoutLayout);
        //Toolbar
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        sessionManager = new SessionManager(this);   //SessionManager Declare
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });
        plan = findViewById(R.id.ProfilePlan);
        name = findViewById(R.id.ProfileName);
        email = findViewById(R.id.ProfileEmail);
        phone = findViewById(R.id.ProfileMobile);
        profession = findViewById(R.id.ProfileProfession);
        city = findViewById(R.id.ProfileCity);

        HashMap<String, String> user = sessionManager.getUserDetail();

        String mCLientPlan = user.get(sessionManager.CLIENT_PLAN);
        String mCLientName = user.get(sessionManager.CLIENT_NAME);
        String mCLientEmail = user.get(sessionManager.CLIENT_EMAIL);
        String mCLientMobile = user.get(sessionManager.CLIENT_MOBILE);

        name.setText(mCLientName);
        phone.setText("+91 "+mCLientMobile);
        email.setText(mCLientEmail);
        if(mCLientPlan.equals("null") || mCLientPlan.equals(null)){
            plan.setText("No Plan Purchased");
        }
        else{
            switch (mCLientPlan){
                case "1":plan.setText("Basic Plan");break;
                case "2":plan.setText("Standard Plan");break;
                case "3":plan.setText("Expert Plan");break;
            }

        }


    }
}
