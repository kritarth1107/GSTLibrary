package in.co.gstsamadhan.gstsamadhan.PaymentGateway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import in.co.gstsamadhan.gstsamadhan.MainActivity;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;

public class TransactionStatus extends AppCompatActivity {
    TextView statusHeader,orderid,amount,mode,txnid,bankid,date;
    Intent i;
    String TxStatus,TxAmount,TxOrderID,TxMode,TxBankId,TxID,Txdate,success_plan_id;
    ImageView BackNav,statusImage;
    LinearLayout statusColor,continueLayout;
    SessionManager sessionManager;
    String mCLientID ;
    String mCLientName ;
    String mCLientEmail ;
    String mCLientMobile ;
    String mClientPlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_status);
        BackNav = findViewById(R.id.BackNav);
        statusHeader = findViewById(R.id.TxnStatusHeader);
        orderid = findViewById(R.id.TXNorderId);
        amount = findViewById(R.id.TXNamount);
        txnid = findViewById(R.id.TXNid);
        date = findViewById(R.id.TXNdate);
        statusColor =  findViewById(R.id.statusColor);
        statusImage =  findViewById(R.id.statusImage);
        continueLayout =  findViewById(R.id.continueLayout);
        sessionManager = new SessionManager(this);

        continueLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitToMain();
            }
        });

        if(sessionManager.isLoggin()) {
            HashMap<String, String> user = sessionManager.getUserDetail();

            mCLientID = user.get(sessionManager.CLIENT_ID);
            mCLientName = user.get(sessionManager.CLIENT_NAME);
            mCLientEmail = user.get(sessionManager.CLIENT_EMAIL);
            mCLientMobile = user.get(sessionManager.CLIENT_MOBILE);
            mClientPlan = user.get(sessionManager.CLIENT_PLAN);

        }
        i = getIntent();
        TxStatus = i.getStringExtra("TxStatus");
        TxAmount = i.getStringExtra("TxAmount");
        TxOrderID = i.getStringExtra("TxOrderID");
        TxMode = i.getStringExtra("TxMode");
        TxBankId = i.getStringExtra("TxBankId");
        TxID = i.getStringExtra("TxID");
        Txdate = i.getStringExtra("Txdate");
        success_plan_id = i.getStringExtra("success_plan_id");
        switch (TxStatus){
            case "TXN_SUCCESS":
                statusColor.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                statusImage.setImageResource(R.drawable.ic_tick_inside_circle);
                statusHeader.setText("Transaction Success");
                break;
            case "TXN_FAILURE":
                statusColor.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                statusHeader.setText("Transaction Failed");
                statusImage.setImageResource(R.drawable.ic_cross);
                break;
            default:
                    statusHeader.setText("Something Went Wrong");
                    statusImage.setImageResource(R.drawable.ic_warning);
                    statusColor.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    break;
        }

        amount.setText(TxAmount);
        txnid.setText(TxID);
        date.setText(Txdate);
        orderid.setText(TxOrderID);


        BackNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitToMain();
            }
        });


    }
    public void exitToMain(){
        Intent intent = new Intent(TransactionStatus.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        exitToMain();
    }
}
