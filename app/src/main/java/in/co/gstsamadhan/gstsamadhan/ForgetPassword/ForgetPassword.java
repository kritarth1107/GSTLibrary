package in.co.gstsamadhan.gstsamadhan.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tuyenmonkey.mkloader.MKLoader;

import in.co.gstsamadhan.gstsamadhan.Login.VerificationActivity;
import in.co.gstsamadhan.gstsamadhan.PricingActivity;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.Registration.Register_Step_2;
import in.co.gstsamadhan.gstsamadhan.model.Registration;
import in.co.gstsamadhan.gstsamadhan.model.Verify;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.co.gstsamadhan.gstsamadhan.Login.LoginWithOTP.INTENT_PHONENUMBER;

public class ForgetPassword extends AppCompatActivity {
    Button VerifyButton;
    EditText mobienum;
    TextView login;
    MKLoader mkLoader;
    RelativeLayout ForgetActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        VerifyButton = findViewById(R.id.VerifyButton);
        mobienum = findViewById(R.id.RegMobileNumber);
        mkLoader = findViewById(R.id.LoginProgressbar);
        ForgetActivity = findViewById(R.id.ForgetActivity);
        login = findViewById(R.id.LoginNow);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(0,0);
            }
        });
        VerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mn = mobienum.getText().toString().trim();
                if(mn.length()<10){mobienum.setError("Invalid Mobile Number");}
                else if(mn.isEmpty()){mobienum.setError("Required Mobile Numbe");}
                else{

                    check(mn);
                }
            }
        });
    }
    public void check(final String Mobile){
        GstSamadhanApi gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);
        Call<Verify> call = gstSamadhanApi.verifyMobile(Mobile
        );
        call.enqueue(new Callback<Verify>() {
            @Override
            public void onResponse(Call<Verify> call, Response<Verify> response) {

                String success = response.body().getStatus().trim();
                if(success.equals("1")){
                    hideKeypad();
                    Intent verification = new Intent(ForgetPassword.this, Forget_Verification.class);
                    verification.putExtra("phoneNumber", Mobile);
                    startActivity(verification);
                    finish();
                    overridePendingTransition(0,0);
                }
                else if(success.equals("0")){
                    hideKeypad();
                    VerifyButton.setVisibility(View.VISIBLE);
                    mkLoader.setVisibility(View.GONE);
                    Snackbar.make(ForgetActivity,"Mobile Number Not Registered With us", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Verify> call, Throwable t) {
                hideKeypad();
                VerifyButton.setVisibility(View.VISIBLE);
                mkLoader.setVisibility(View.GONE);
                Toast.makeText(ForgetPassword.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void hideKeypad() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,0);
    }
}
