package in.co.gstsamadhan.gstsamadhan.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.msg91.sendotpandroid.library.PhoneNumberUtils;
import com.msg91.sendotpandroid.library.internal.Iso2Phone;
import com.tuyenmonkey.mkloader.MKLoader;

import java.io.IOException;
import java.util.Locale;

import in.co.gstsamadhan.gstsamadhan.R;

public class LoginWithOTP extends AppCompatActivity {
    public static final String INTENT_PHONENUMBER = "phonenumber";

    private EditText mPhoneNumber;
    private Button mSmsButton;
    MKLoader LoginProgressbar;
    String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_with_otp);
        LoginProgressbar = findViewById(R.id.LoginProgressbar);
        mPhoneNumber = (EditText) findViewById(R.id.RegMobileNumber);
        mSmsButton = (Button) findViewById(R.id.LoginButton);
        mSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = getE164Number();
                openActivity(phoneNumber);

            }
        });


    }




    private void openActivity(String phoneNumber) {
        Intent verification = new Intent(this, VerificationActivity.class);
        verification.putExtra(INTENT_PHONENUMBER, phoneNumber);
        startActivity(verification);
        overridePendingTransition(0,0);
    }

    private void setButtonsEnabled(boolean enabled) {
        mSmsButton.setEnabled(enabled);
    }



    private String getE164Number() {
        return mPhoneNumber.getText().toString().replaceAll("\\D", "").trim();
        // return PhoneNumberUtils.formatNumberToE164(mPhoneNumber.getText().toString(), mCountryIso);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,0);
    }
}
