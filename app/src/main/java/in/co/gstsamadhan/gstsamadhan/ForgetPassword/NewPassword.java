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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tuyenmonkey.mkloader.MKLoader;

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

public class NewPassword extends AppCompatActivity {
    String phoneNumber;
    EditText newPassword,cnfnewPassword;
    Button chnagepwd;
    RelativeLayout NewPwd;
    MKLoader mkLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        Intent getstring = getIntent();
        phoneNumber = getstring.getStringExtra("phoneNumber");
        newPassword = findViewById(R.id.newPassword);
        cnfnewPassword = findViewById(R.id.cnfNewpassword);
        chnagepwd = findViewById(R.id.changepwd);
        NewPwd = findViewById(R.id.NewPwd);
        mkLoader = findViewById(R.id.LoginProgressbar);
        chnagepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeypad();
                String pwd = newPassword.getText().toString().trim();
                String pwd2 = cnfnewPassword.getText().toString().trim();
                if(pwd.isEmpty() || pwd2.isEmpty()){
                    Snackbar.make(NewPwd,"Please Enter Password", Snackbar.LENGTH_LONG).show();
                }
                else {
                    if(pwd.length()<8){
                        newPassword.setError("Length Must be min. 8");
                        Snackbar.make(NewPwd,"Password Length Must be Min. 8", Snackbar.LENGTH_LONG).show();
                    }
                    else {
                        if(pwd.equals(pwd2)){
                            change(pwd);
                        }
                        else{
                            cnfnewPassword.setError("Password Dosen't Match");
                            Snackbar.make(NewPwd,"Password Dosen't Match", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }


            }
        });


    }
    public void change(String Pwd){
        GstSamadhanApi gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);
        Call<Verify> call = gstSamadhanApi.setForgetPassword(phoneNumber,Pwd
        );
        call.enqueue(new Callback<Verify>() {
            @Override
            public void onResponse(Call<Verify> call, Response<Verify> response) {

                String success = response.body().getSuccess().trim();
                if(success.equals("1")){
                    Toast.makeText(NewPassword.this, "Password Changed Succesfully! Login Now", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(success.equals("0")){
                    Toast.makeText(NewPassword.this, "Something Went Wrong at Our End", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Verify> call, Throwable t) {
                Toast.makeText(NewPassword.this, t.toString(), Toast.LENGTH_SHORT).show();
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
