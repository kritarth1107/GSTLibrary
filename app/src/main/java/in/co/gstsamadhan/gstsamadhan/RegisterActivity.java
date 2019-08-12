package in.co.gstsamadhan.gstsamadhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    RelativeLayout RegisterActivity;
    LinearLayout Reg_LoginNavigate;
    EditText RegEmail,RegPassword,RegFullName,RegMobile;
    Button RegButton;
    TextView Reg_TS,Reg_PN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //get Elements by ID
        RegisterActivity= findViewById(R.id.RegisterActivity);
        Reg_LoginNavigate = findViewById(R.id.Reg_LoginNavigate);
        RegEmail = findViewById(R.id.RegEmailAddress);
        RegPassword = findViewById(R.id.RegPassword);
        RegFullName = findViewById(R.id.RegFullName);
        RegMobile = findViewById(R.id.RegMobileNumber);
        RegButton = findViewById(R.id.RegButton);
        Reg_TS = findViewById(R.id.Reg_TermOFService);
        Reg_PN = findViewById(R.id.Reg_PrivacyNotice);

        Reg_LoginNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=RegEmail.getText().toString().trim();
                String name = RegFullName.getText().toString().trim();
                String mobile = RegMobile.getText().toString().trim();
                String password = RegPassword.getText().toString().trim();
                if(name.isEmpty()){
                    RegFullName.setError("required");
                    Snackbar.make(RegisterActivity,"Please Enter Full Name", Snackbar.LENGTH_LONG).show();
                }
                else if(Email.isEmpty()){
                    RegEmail.setError("required");
                    Snackbar.make(RegisterActivity,"Please Enter Email Address", Snackbar.LENGTH_LONG).show();
                }
                else if(mobile.isEmpty()){
                    RegMobile.setError("required");
                    Snackbar.make(RegisterActivity,"Please Enter Mobile Number", Snackbar.LENGTH_LONG).show();
                }
                else if(mobile.length()!=10){
                    RegMobile.setError("Length must be 10");
                    Snackbar.make(RegisterActivity,"Please Enter valid Mobile Number", Snackbar.LENGTH_LONG).show();
                }
                else if(password.isEmpty()){
                    RegPassword.setError("required");
                    Snackbar.make(RegisterActivity,"Please choose a password", Snackbar.LENGTH_LONG).show();
                }
                else if(password.length()<8){
                    RegPassword.setError("Password Length must be min.8 Character");
                    Snackbar.make(RegisterActivity,"Please choose valid Password", Snackbar.LENGTH_LONG).show();
                }
                else{
                    Register(name,Email,password,mobile);
                }


            }
        });
    }
    public void Register(final String Name,final String Email, final String Password, final String Mobile){
        Snackbar.make(RegisterActivity,"Here We Proceed to Registration Procedure", Snackbar.LENGTH_LONG).show();
    }
}
