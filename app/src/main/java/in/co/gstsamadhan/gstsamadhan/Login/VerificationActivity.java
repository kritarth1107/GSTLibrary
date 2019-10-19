package in.co.gstsamadhan.gstsamadhan.Login;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.msg91.sendotpandroid.library.IPConverter;
import com.msg91.sendotpandroid.library.SendOTPConfig;
import com.msg91.sendotpandroid.library.SendOtpVerification;
import com.msg91.sendotpandroid.library.Verification;
import com.msg91.sendotpandroid.library.VerificationListener;
import com.tuyenmonkey.mkloader.MKLoader;

import in.co.gstsamadhan.gstsamadhan.MainActivity;
import in.co.gstsamadhan.gstsamadhan.PricingActivity;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.Registration.Register_Step_2;
import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;
import in.co.gstsamadhan.gstsamadhan.model.Registration;
import in.co.gstsamadhan.gstsamadhan.model.User;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback, VerificationListener {

    private static final String TAG = Verification.class.getSimpleName();
    TextView resend_timer;
    private Verification mVerification;
    Button verify;
    SessionManager sessionManager;
    String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        resend_timer = (TextView) findViewById(R.id.resend_timer);
        sessionManager = new SessionManager(this);
        resend_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResendCode();
            }
        });
        startTimer();
        enableInputField(true);
        initiateVerification();
        verify = findViewById(R.id.codeInputButton);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = ((EditText) findViewById(R.id.inputCode)).getText().toString();
                if (!code.isEmpty()) {
                    hideKeypad();
                    if (mVerification != null) {
                        mVerification.verify(code);
                        showProgress();
                        TextView messageText = (TextView) findViewById(R.id.textView);
                        messageText.setText("Verification in progress");
                        enableInputField(false);
                    }
                }
            }
        });
    }

    void createVerification(String phoneNumber, boolean skipPermissionCheck, String countryCode) {

            boolean withoutOtp = false;
            if (NetworkConnectivity.isConnectedMobileNetwork(getApplicationContext())) {
                withoutOtp = true;
            } else {

            }


            SendOTPConfig otpConfig = SendOtpVerification
                    .config(countryCode + phoneNumber)
                    .context(this)
                    .httpsConnection(true)//secure connections setting
                    //////////////////direct verification while connect with mobile network/////////////////////////
                    .autoVerification(false)
                    .verifyWithoutOtp(withoutOtp)
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    .unicode(false) // set true if you want to use unicode (or other language) in sms
                    .expiry("5")//value in minutes
                    .senderId("GSTSMD") //where ABCDEF is any string
                    .otplength("6") //length of your otp max length up to 9 digits
                    //--------case 1-------------------
//                            .message("##OTP## is Your verification digits.")//##OTP## use for default generated OTP
                    //--------case 2-------------------
                    /*  .otp("1234")// Custom Otp code, if want to add yours
                      .message("1234 is Your verification digits.")//Here 1234 same as above Custom otp.*/
                    //-------------------------------------
                    //use single case at a time either 1 or 2
                    .build();
            mVerification = SendOtpVerification.createSmsVerification
                    (otpConfig, this);
            mVerification.initiate();

    }






    void initiateVerification() {
        initiateVerification(false);
    }


    void initiateVerification(boolean skipPermissionCheck) {
        Intent intent = getIntent();
        if (intent != null) {
            phoneNumber = intent.getStringExtra(LoginWithOTP.INTENT_PHONENUMBER);
            TextView phoneText = (TextView) findViewById(R.id.numberText);
            phoneText.setText("+" + "91" + phoneNumber);
            createVerification(phoneNumber, skipPermissionCheck, "91");
        }
    }

    public void ResendCode() {
        startTimer();
        mVerification.resend("voice");
    }

    public void onSubmitClicked(View view) {

    }

    void enableInputField(boolean enable) {
        View container = findViewById(R.id.inputContainer);
        if (enable) {
            container.setVisibility(View.VISIBLE);
            EditText input = (EditText) findViewById(R.id.inputCode);
            input.requestFocus();
        } else {
            container.setVisibility(View.GONE);
        }
        TextView resend_timer = (TextView) findViewById(R.id.resend_timer);
        resend_timer.setClickable(false);
    }

    void hideProgressBarAndShowMessage(int message) {
        hideProgressBar();
        TextView messageText = (TextView) findViewById(R.id.textView);
        messageText.setText(message);
    }

    void hideProgressBar() {
        MKLoader progressBar = findViewById(R.id.LoginProgressbar);
        progressBar.setVisibility(View.INVISIBLE);
        TextView progressText = (TextView) findViewById(R.id.progressText);
        progressText.setVisibility(View.INVISIBLE);
    }

    void showProgress() {
        MKLoader progressBar = (MKLoader) findViewById(R.id.LoginProgressbar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInitiated(String response) {
        Log.d(TAG, "Initialized!" + response);
    }

    @Override
    public void onInitiationFailed(Exception exception) {
        Log.e(TAG, "Verification initialization failed: " + exception.getMessage());
        hideProgressBarAndShowMessage(R.string.failed);
    }

    @Override
    public void onVerified(String response) {
        enableInputField(false);
        Log.d(TAG, "Verified!\n" + response);
        hideKeypad();
        LoginNow(phoneNumber);
    }

    @Override
    public void onVerificationFailed(Exception exception) {
        Log.e(TAG, "Verification failed: " + exception.getMessage());
        hideKeypad();
        hideProgressBarAndShowMessage(R.string.failed);
        enableInputField(true);
    }

    private void startTimer() {
        resend_timer.setClickable(false);
        resend_timer.setTextColor(ContextCompat.getColor(VerificationActivity.this, android.R.color.darker_gray));
        new CountDownTimer(30000, 1000) {
            int secondsLeft = 0;

            public void onTick(long ms) {
                if (Math.round((float) ms / 1000.0f) != secondsLeft) {
                    secondsLeft = Math.round((float) ms / 1000.0f);
                    resend_timer.setText("Resend via call ( " + secondsLeft + " )");
                }
            }

            public void onFinish() {
                resend_timer.setClickable(true);
                resend_timer.setText("Resend via call");
                resend_timer.setTextColor(ContextCompat.getColor(VerificationActivity.this, R.color.colorPrimary));
            }
        }.start();
    }

    private void hideKeypad() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
/*        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities(mainIntent, 0)*/
    }

    public void LoginNow(final String mobile)
    {

        GstSamadhanApi gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);
        Call<User> call = gstSamadhanApi.getMobileLogin(mobile
        );
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                String success = response.body().getLoginstatus().trim();
                if(success.equals("1")){
                    String client_id = response.body().getId().trim();
                    String client_name = response.body().getFname().trim();
                    String client_email = response.body().getEmail().trim();
                    String client_mobile = response.body().getMobile().trim();
                    String client_plan = response.body().getPlan_id().trim();
                    sessionManager.createSession(client_id,client_name,client_email,client_mobile,client_plan);
                    Intent i = new Intent(VerificationActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);

                }
                else if(success.equals("0")){
                    Toast.makeText(VerificationActivity.this, "Mobile Number Not Registered", Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition(0,0  );

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(VerificationActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,0);
    }
}