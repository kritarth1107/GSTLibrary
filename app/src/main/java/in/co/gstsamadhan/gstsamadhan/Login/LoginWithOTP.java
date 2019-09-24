package in.co.gstsamadhan.gstsamadhan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import in.co.gstsamadhan.gstsamadhan.R;

public class LoginWithOTP extends AppCompatActivity {
    TextView LoginWithPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_otp);
        LoginWithPassword = findViewById(R.id.LoginWithPassword);
        LoginWithPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,0);
    }
}
