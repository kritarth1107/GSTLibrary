package in.co.gstsamadhan.gstsamadhan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.tuyenmonkey.mkloader.MKLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.co.gstsamadhan.gstsamadhan.ForgetPassword.ForgetPassword;
import in.co.gstsamadhan.gstsamadhan.MainActivity;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.Registration.RegisterActivity;
import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout LoginActivity;
    EditText LoginEmail,LoginPassword;
    Button LoginButton;
    TextView LoginForgotPassword,Login_TermsOfService,Login_PrivacyNotice,LoginWithOTP;
    LinearLayout Login_CreateAccount;
    MKLoader progressBar;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(this);
        //GetItemsById
        LoginActivity = findViewById(R.id.LoginActivity);
        LoginEmail = findViewById(R.id.LoginEmail);
        LoginPassword = findViewById(R.id.LoginPassword);
        LoginButton = findViewById(R.id.LoginButton);
        LoginForgotPassword = findViewById(R.id.LoginForgotPassword);
        Login_TermsOfService = findViewById(R.id.Login_TermsOfService);
        Login_PrivacyNotice = findViewById(R.id.Login_PrivacyNotice);
        LoginWithOTP = findViewById(R.id.LoginWithOTP);
        Login_CreateAccount = findViewById(R.id.Login_CreateAccount);
        progressBar = findViewById(R.id.LoginProgressbar);
        progressBar.setVisibility(View.GONE);
        //open Register Activity
        Login_CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg = new Intent(LoginActivity.this, RegisterActivity.class);
                Reg.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(Reg);
                overridePendingTransition(0,0);
            }
        });

        //On Login Button Click
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EmailAddress = LoginEmail.getText().toString().trim();
                String Password = LoginPassword.getText().toString().trim();
                    if(EmailAddress.isEmpty()){
                        LoginEmail.setError("Required");
                        Snackbar.make(LoginActivity,"Please Enter valid Email Address", Snackbar.LENGTH_LONG).show();
                    }
                 else if(Password.isEmpty()){
                     LoginPassword.setError("Required");
                    Snackbar.make(LoginActivity,"Please Enter valid Password", Snackbar.LENGTH_LONG).show();
                }
                 else{
                        Login(EmailAddress,Password);
                    }
            }
        });
        LoginWithOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,LoginWithOTP.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });
        LoginForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(in.co.gstsamadhan.gstsamadhan.Login.LoginActivity.this, ForgetPassword.class));
                overridePendingTransition(0,0);
            }
        });

    }
    public void Login(final String Email,final String Password){
        progressBar.setVisibility(View.VISIBLE);
        LoginButton.setVisibility(View.GONE);
        StringRequest request = new StringRequest(Request.Method.POST, "https://gstsamadhan.co.in/gstapi/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("loginstatus");

                    if(success.equals("1")){
                        String client_id = jsonObject.getString("id").trim();
                        String client_plan = jsonObject.getString("plan_id").trim();
                        String client_name = jsonObject.getString("fname").trim();
                        String client_email = jsonObject.getString("email").trim();
                        String client_mobile = jsonObject.getString("mobile").trim();
                        sessionManager.createSession(client_id,client_name,client_email,client_mobile,client_plan);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


                    }
                    else if(success.equals("0")){
                        Snackbar.make(LoginActivity,"Email & Password Missmatch", Snackbar.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        LoginButton.setVisibility(View.VISIBLE);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    LoginButton.setVisibility(View.VISIBLE);


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",Email);
                params.put("password",Password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}
