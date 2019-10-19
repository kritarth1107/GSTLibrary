package in.co.gstsamadhan.gstsamadhan.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.co.gstsamadhan.gstsamadhan.Login.LoginActivity;
import in.co.gstsamadhan.gstsamadhan.MainActivity;
import in.co.gstsamadhan.gstsamadhan.PaymentGateway.checksum;
import in.co.gstsamadhan.gstsamadhan.PricingActivity;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;
import in.co.gstsamadhan.gstsamadhan.model.Registration;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Step_2 extends AppCompatActivity {
    Intent intent;
    String Name,Email,Password,Mobile;
    EditText RegCity,RegPincode;
    Button Register,Skip;
    Spinner RegState,RegProfession;
    RelativeLayout RegisterActivity;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__step_2);
        Skip = findViewById(R.id.RegButtonSkip);
        intent = getIntent();
        RegisterActivity = findViewById(R.id.RegisterActivity);
        Name = intent.getStringExtra("name");
        Email = intent.getStringExtra("email");
        Password = intent.getStringExtra("password");
        Mobile = intent.getStringExtra("mobile");
        sessionManager = new SessionManager(this);

        RegState = findViewById(R.id.RegState);
        RegCity = findViewById(R.id.RegCity);
        RegProfession = findViewById(R.id.RegProfession);
        RegPincode = findViewById(R.id.RegPincode);
        Register = findViewById(R.id.RegButtonStep2);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RegState.getSelectedItemId()==0 || RegProfession.getSelectedItemId()==0){
                    Toast.makeText(Register_Step_2.this, "Please Select Required Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    String State = RegState.getSelectedItem().toString().trim();
                    String City = RegCity.getText().toString().trim();
                    String Profession = RegProfession.getSelectedItem().toString().trim();
                    String Pincode = RegPincode.getText().toString().trim();

                    RegisterNow(State,City,Profession,Pincode);
                }

            }
        });

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterNow(null,null,null,null);
            }
        });

    }


    public void RegisterNow(final String State,final String City, final String Profession,final String Pincode)
    {

        GstSamadhanApi gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);
        Call<Registration> call = gstSamadhanApi.register(
                Name,Email,Mobile,Password,State,Profession,City,Pincode
        );
        call.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration> call, Response<Registration> response) {

                String success = response.body().getRegistration().trim();
                if(success.equals("1")){
                    String client_id = response.body().getId().trim();
                    String client_name = response.body().getFname().trim();
                    String client_email = response.body().getEmail().trim();
                    String client_mobile = response.body().getMobile().trim();
                    sessionManager.createSession(client_id,client_name,client_email,client_mobile,"null");
                    Intent i = new Intent(Register_Step_2.this, PricingActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("error","Registration");
                    startActivity(i);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
                else if(success.equals("0")){
                    String error = response.body().getError().trim();
                    Snackbar.make(RegisterActivity,error, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Registration> call, Throwable t) {
                Toast.makeText(Register_Step_2.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        /*
        StringRequest request = new StringRequest(Request.Method.POST, "http://gstsamadhan.co.in/gstapi/register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("registration");
                    if(success.equals("1")){
                        String client_id = jsonObject.getString("id").trim();
                        String client_name = jsonObject.getString("fname").trim();
                        String client_email = jsonObject.getString("email").trim();
                        String client_mobile = jsonObject.getString("mobile").trim();
                        sessionManager.createSession(client_id,client_name,client_email,client_mobile,"null");
                        Intent i = new Intent(Register_Step_2.this, PricingActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }
                    else if(success.equals("0")){
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(Register_Step_2.this, "Error "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register_Step_2.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",Name);
                params.put("email",Email);
                params.put("mobile",Mobile);
                params.put("password",Password);
                params.put("state",State);
                params.put("city",City);
                params.put("profession",Profession);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        */
    }
}