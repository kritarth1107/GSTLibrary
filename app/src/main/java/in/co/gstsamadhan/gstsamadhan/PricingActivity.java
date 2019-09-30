package in.co.gstsamadhan.gstsamadhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class PricingActivity extends AppCompatActivity {
    Intent i;
    ImageView back;
    RelativeLayout mainPricing;
    TextView skip,nothanks;
    CardView Basic,Expert,Standard;
    String intentError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);
        back = findViewById(R.id.BackNav);
        i= getIntent();
        intentError = i.getStringExtra("error");

        Expert = findViewById(R.id.ExpertPlan);
        Standard = findViewById(R.id.StandardPlan);
        Basic  = findViewById(R.id.BasicPlan);
        Basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PricingActivity.this,CheckoutActivity.class);
                intent.putExtra("Plan","1");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        Standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PricingActivity.this,CheckoutActivity.class);
                intent.putExtra("Plan","2");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        Expert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PricingActivity.this,CheckoutActivity.class);
                intent.putExtra("Plan","3");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });


        mainPricing = findViewById(R.id.mainPricing);
        skip = findViewById(R.id.skipTv);
        nothanks = findViewById(R.id.NoThanksTv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intentError.equals("Not-Purchased")){
                    finish();
                }
                else if(intentError.equals("Registration")){
                    Intent i = new Intent(PricingActivity.this,MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intentError.equals("Not-Purchased")){
                    finish();
                }
                else if(intentError.equals("Registration")){
                    Intent i = new Intent(PricingActivity.this,MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            }
        });
        nothanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(intentError.equals("Not-Purchased")){
                    finish();
                }
                else if(intentError.equals("Registration")){
                    Intent i = new Intent(PricingActivity.this,MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            }
        });

        if(intentError.equals("Not-Purchased")){
            Snackbar.make(mainPricing,"Please Purchase a Plan to Access this Content", Snackbar.LENGTH_LONG).show();
        }
        else if(intentError.equals("Registration")){

        }
    }

    @Override
    public void onBackPressed() {

        if(intentError.equals("Not-Purchased")){
            finish();
        }
        else if(intentError.equals("Registration")){
            Intent i = new Intent(PricingActivity.this,MainActivity.class);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }


    }
}
