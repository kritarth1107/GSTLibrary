package in.co.gstsamadhan.gstsamadhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.co.gstsamadhan.gstsamadhan.PaymentGateway.Constants;
import in.co.gstsamadhan.gstsamadhan.PaymentGateway.Paytm;
import in.co.gstsamadhan.gstsamadhan.PaymentGateway.TransactionStatus;
import in.co.gstsamadhan.gstsamadhan.PaymentGateway.checksum;
import in.co.gstsamadhan.gstsamadhan.Registration.Register_Step_2;
import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;
import in.co.gstsamadhan.gstsamadhan.model.Acts;
import in.co.gstsamadhan.gstsamadhan.model.Coupon;
import in.co.gstsamadhan.gstsamadhan.model.Purchase;
import in.co.gstsamadhan.gstsamadhan.model.Registration;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckoutActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {
    ImageView back;
    Intent i;
    TextView planTitle,planAmount,payableAmount,planTax,PlanFinalAmount,planDisscount,PlanFinalAmmountRound,planExpiry,errorText;
    TextView havePromo,promoApply,Remove;
    LinearLayout promoSection,disscountLayout,invalid,applied;
    double baseammount=0,disscount=0,tax=0,finalammount=0;
    String pay;
    int planDuration =0,dr=0;
    Spinner spinner;
    String PromoCode="None",date,year,month;
    EditText PromoEt;
    CardView paynow;
    SessionManager sessionManager;
    String mCLientID ;
    String mCLientName ;
    String mCLientEmail ;
    String mCLientMobile ;
    String mClientPlan;
    String discountType="None",discount="0";
    String TransactionID;
    String Plan;
    double d=0;
    String pp=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        sessionManager = new SessionManager(this);   //SessionManager Declare
        paynow  = findViewById(R.id.PayNowButton);
        invalid = findViewById(R.id.invalid);
        Remove = findViewById(R.id.Remove);
        errorText = findViewById(R.id.errorText);
        applied = findViewById(R.id.applied);
        planTitle = findViewById(R.id.PlanTitle);
        planAmount = findViewById(R.id.PlanAmmount);
        payableAmount = findViewById(R.id.PayableAmount);
        planTax = findViewById(R.id.PlanTax);
        PlanFinalAmount = findViewById(R.id.PlanFinalAmmount);
        planExpiry = findViewById(R.id.PlanExpiry);
        PlanFinalAmmountRound = findViewById(R.id.PlanFinalAmmountRound);
        spinner = findViewById(R.id.duration);
        promoSection = findViewById(R.id.PromoLayout);
        havePromo = findViewById(R.id.havePromo);
        promoApply = findViewById(R.id.ApplyPromo);
        PromoEt = findViewById(R.id.PromoEt);
        disscountLayout = findViewById(R.id.disscountLayout);
        planDisscount = findViewById(R.id.PlanDisscount);
        havePromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promoSection.setVisibility(View.VISIBLE);
                promoSection.setAnimation(AnimationUtils.loadAnimation(CheckoutActivity.this,R.anim.fade_up_down));
            }
        });
        DateFormat df = new SimpleDateFormat("dd");
        date = df.format(Calendar.getInstance().getTime());

        DateFormat mf = new SimpleDateFormat("MM");
        month =mf.format(Calendar.getInstance().getTime());

        DateFormat tx = new SimpleDateFormat("yyyyMMddhhmmss");
        TransactionID =tx.format(Calendar.getInstance().getTime());

        if(sessionManager.isLoggin()) {
            HashMap<String, String> user = sessionManager.getUserDetail();

            mCLientID = user.get(sessionManager.CLIENT_ID);
            mCLientName = user.get(sessionManager.CLIENT_NAME);
            mCLientEmail = user.get(sessionManager.CLIENT_EMAIL);
            mCLientMobile = user.get(sessionManager.CLIENT_MOBILE);
            mClientPlan = user.get(sessionManager.CLIENT_PLAN);

        }
        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateCheckSum(mCLientID);
            }
        });




        back = findViewById(R.id.BackNav);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        if (ContextCompat.checkSelfPermission(CheckoutActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CheckoutActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
        i= getIntent();
        Plan = i.getStringExtra("Plan");
        promoApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    PromoCode = PromoEt.getText().toString().trim();
                    pp=PromoCode;
                    if(PromoCode.isEmpty()){
                        PromoEt.setError("Empty Code");
                    }
                    else{
                        GstSamadhanApi gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);
                        Call<Coupon> call = gstSamadhanApi.getCoupon(PromoCode);
                        call.enqueue(new Callback<Coupon>() {
                            @Override
                            public void onResponse(Call<Coupon> call, Response<Coupon> response) {

                                final String success= response.body().getCoupon_status().trim();
                                if(success.equals("Active")){
                                    promoSection.setVisibility(View.GONE);
                                    applied.setVisibility(View.VISIBLE);
                                    invalid.setVisibility(View.GONE);
                                    havePromo.setVisibility(View.GONE);
                                    discountType = response.body().getDiscount_type().trim();
                                    discount = response.body().getDiscount().trim();
                                    d = Double.parseDouble(discount);
                                    assignValues(Plan,PromoCode,dr,d,discountType);
                                }
                                else {
                                    errorText.setText(success);
                                    invalid.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onFailure(Call<Coupon> call, Throwable t) {
                            }
                        });


                    }

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                assignValues(Plan,PromoCode,position,d,discountType);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        assignValues(Plan,PromoCode,dr,d,discountType);
        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PromoCode = "None";
                pp=null;
                d = 0;
                discountType = "None";
                assignValues(Plan,PromoCode,planDuration-1,d,discountType);
                promoSection.setVisibility(View.VISIBLE);
                applied.setVisibility(View.GONE);
                havePromo.setVisibility(View.VISIBLE);
            }
        });








    }
    public void assignValues(String Plan,String PromoCode,int Duration,double disc,String type){
        planDuration = Duration+1;
        DateFormat yf = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,planDuration);
        year = yf.format(cal.getTime());
        planExpiry.setText(year+"-"+month+"-"+date);
        switch (Plan){
            case "1":
                planTitle.setText("Basic Plan");
                baseammount = 4999*planDuration;
                planAmount.setText(String.valueOf(baseammount));

                if(!PromoCode.equals("None")){
                    if(type.equals("FLAT")){
                        disscountLayout.setVisibility(View.VISIBLE);
                        disscount = baseammount-(baseammount-disc);
                        tax = 0.18*(baseammount-disscount);
                        planDisscount.setText(new DecimalFormat("##.##").format(disscount));
                        finalammount = baseammount-disscount+tax;
                        PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                        planTax.setText(new DecimalFormat("##.##").format(tax));
                    }
                    else{
                        disscountLayout.setVisibility(View.VISIBLE);
                        disscount = (disc/100)*baseammount;
                        tax = 0.18*(baseammount-disscount);
                        planDisscount.setText(new DecimalFormat("##.##").format(disscount));
                        finalammount = baseammount-disscount+tax;
                        PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                        planTax.setText(new DecimalFormat("##.##").format(tax));
                    }

                }
                else{

                    disscountLayout.setVisibility(View.GONE);
                    disscount = 0;
                    tax = 0.18*(baseammount-disscount);
                    finalammount = baseammount-disscount+tax;
                    PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                    planTax.setText(new DecimalFormat("##.##").format(tax));
                }
                PlanFinalAmmountRound.setText(new DecimalFormat("##").format(finalammount));
                pay = new DecimalFormat("##").format(finalammount);
                payableAmount.setText(new DecimalFormat("##").format(finalammount));
                break;
            case "2":
                planTitle.setText("Standard Plan");
                baseammount = 7499*planDuration;
                planAmount.setText(String.valueOf(baseammount));

                if(!PromoCode.equals("None")){
                    if(type.equals("FLAT")){
                        disscountLayout.setVisibility(View.VISIBLE);
                        disscount = baseammount-(baseammount-disc);
                        tax = 0.18*(baseammount-disscount);
                        planDisscount.setText(new DecimalFormat("##.##").format(disscount));
                        finalammount = baseammount-disscount+tax;
                        PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                        planTax.setText(new DecimalFormat("##.##").format(tax));
                    }
                    else{
                        disscountLayout.setVisibility(View.VISIBLE);
                        disscount = (disc/100)*baseammount;
                        tax = 0.18*(baseammount-disscount);
                        planDisscount.setText(new DecimalFormat("##.##").format(disscount));
                        finalammount = baseammount-disscount+tax;
                        PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                        planTax.setText(new DecimalFormat("##.##").format(tax));
                    }

                }
                else{

                    disscountLayout.setVisibility(View.GONE);
                    disscount = 0;
                    tax = 0.18*(baseammount-disscount);
                    finalammount = baseammount-disscount+tax;
                    PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                    planTax.setText(new DecimalFormat("##.##").format(tax));
                }
                PlanFinalAmmountRound.setText(new DecimalFormat("##").format(finalammount));
                pay = new DecimalFormat("##").format(finalammount);
                payableAmount.setText(new DecimalFormat("##").format(finalammount));
                break;
            case "3":
                planTitle.setText("Expert Plan");
                baseammount = 9999*planDuration;
                planAmount.setText(String.valueOf(baseammount));

                if(!PromoCode.equals("None")){
                    if(type.equals("FLAT")){
                        disscountLayout.setVisibility(View.VISIBLE);
                        disscount = baseammount-(baseammount-disc);
                        tax = 0.18*(baseammount-disscount);
                        planDisscount.setText(new DecimalFormat("##.##").format(disscount));
                        finalammount = baseammount-disscount+tax;
                        PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                        planTax.setText(new DecimalFormat("##.##").format(tax));
                    }
                    else{
                        disscountLayout.setVisibility(View.VISIBLE);
                        disscount = (disc/100)*baseammount;
                        tax = 0.18*(baseammount-disscount);
                        planDisscount.setText(new DecimalFormat("##.##").format(disscount));
                        finalammount = baseammount-disscount+tax;
                        PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                        planTax.setText(new DecimalFormat("##.##").format(tax));
                    }

                }
                else{

                    disscountLayout.setVisibility(View.GONE);
                    disscount = 0;
                    tax = 0.18*(baseammount-disscount);
                    finalammount = baseammount-disscount+tax;
                    PlanFinalAmount.setText(new DecimalFormat("##.##").format(finalammount));
                    planTax.setText(new DecimalFormat("##.##").format(tax));
                }
                PlanFinalAmmountRound.setText(new DecimalFormat("##").format(finalammount));
                pay = new DecimalFormat("##").format(finalammount);
                payableAmount.setText(new DecimalFormat("##").format(finalammount));
                break;
        }
    }
    public void SetData(double baseammount){





    }
    private void generateCheckSum(String mCLientID) {


        GstSamadhanApi gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);

        //creating paytm object
        //containing all the values required
        final Paytm paytm = new Paytm(
                Constants.M_ID,
                Constants.CHANNEL_ID,
                pay,
                Constants.WEBSITE,
                Constants.CALLBACK_URL,
                Constants.INDUSTRY_TYPE_ID
        );

        //creating a call object from the apiService
        Call<checksum> call = gstSamadhanApi.getChecksum(
                paytm.getmId(),
                paytm.getOrderId(),
                paytm.getCustId(),
                paytm.getChannelId(),
                paytm.getTxnAmount(),
                paytm.getWebsite(),
                paytm.getCallBackUrl(),
                paytm.getIndustryTypeId()
        );

        //making the call to generate checksum
        call.enqueue(new Callback<checksum>() {
            @Override
            public void onResponse(Call<checksum> call, Response<checksum> response) {

                //once we get the checksum we will initiailize the payment.
                //the method is taking the checksum we got and the paytm object as the parameter
                if(paytm.getTxnAmount().equals("0")){
                    submitTransaction("TXN_SUCCESS","0",paytm.getOrderId(),"No-PaymentMode","No_Bank_Transaction",TransactionID,"00-abc-0000");

                }
                else{
                    initializePaytmPayment(response.body().getChecksumHash(), paytm);
                }

            }

            @Override
            public void onFailure(Call<checksum> call, Throwable t) {

            }
        });
    }

    private void initializePaytmPayment(String checksumHash, Paytm paytm) {

        //getting paytm service
        PaytmPGService Service = PaytmPGService.getProductionService();

        //use this when using for production
        //PaytmPGService Service = PaytmPGService.getProductionService();

        //creating a hashmap and adding all the values required
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("MID", Constants.M_ID);
        paramMap.put("ORDER_ID", paytm.getOrderId());
        paramMap.put("CUST_ID", paytm.getCustId());
        paramMap.put("CHANNEL_ID", paytm.getChannelId());
        paramMap.put("TXN_AMOUNT", paytm.getTxnAmount());
        paramMap.put("WEBSITE", paytm.getWebsite());
        paramMap.put("CALLBACK_URL", paytm.getCallBackUrl());
        paramMap.put("CHECKSUMHASH", checksumHash);
        paramMap.put("INDUSTRY_TYPE_ID", paytm.getIndustryTypeId());


        //creating a paytm order object using the hashmap
        PaytmOrder order = new PaytmOrder(paramMap);

        //intializing the paytm service
        Service.initialize(order, null);

        //finally starting the payment transaction
        Service.startPaymentTransaction(this, true, true, this);

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void onTransactionResponse(Bundle inResponse) {
        String status = inResponse.getString("STATUS");
        String checksumhash = inResponse.getString("CHECKSUMHASH");
        String TXNID = inResponse.getString("TXNID");
        String ORDERID = inResponse.getString("ORDERID");
        String TXNDATE = inResponse.getString("TXNDATE");
        String RESPCODE = inResponse.getString("RESPCODE");
        String BANKTXNID = inResponse.getString("BANKTXNID");
        String TXNAMOUNT = inResponse.getString("TXNAMOUNT");
        String PAYMENTMODE = inResponse.getString("PAYMENTMODE");
        String CURRENCY = inResponse.getString("CURRENCY");
        String GATEWAYNAME = inResponse.getString("GATEWAYNAME");
        String RESPMSG = inResponse.getString("RESPMSG");
        String BANKNAME = inResponse.getString("BANKNAME");
        String shareBody = inResponse.toString();


        /*
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share Via")); */


        submitTransaction(status,TXNAMOUNT,ORDERID,"PAYTM",BANKTXNID,TXNID,TXNDATE);

    }

    @Override
    public void networkNotAvailable() {
        Toast.makeText(this, "Network error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clientAuthenticationFailed(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void someUIErrorOccurred(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Toast.makeText(this, "Back Pressed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Toast.makeText(this, s + bundle.toString(), Toast.LENGTH_LONG).show();
    }

    public void submitTransaction(final String status, final String TXNAMOUNT, final String ORDERID, final String PAYMENTMODE, final String BANKTXNID, final String TXNID, final String TXNDATE){

        String base = planAmount.getText().toString().trim();
        String planDisc = planDisscount.getText().toString().trim() ;
        String planGST = planTax.getText().toString().trim();
        String exp_date= planExpiry.getText().toString().trim();
        String rangePlan=null;
        long range = spinner.getSelectedItemId();
        if(range==0){rangePlan="1";}
        else if(range==1){rangePlan="2";}
        else if(range==2){rangePlan="3";}
        else if(range==3){rangePlan="4";}
        else if(range==4){rangePlan="5";}
        GstSamadhanApi gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);
        Call<Purchase> call = gstSamadhanApi.submitTransaction(
                mCLientID,Plan,TXNID,rangePlan,base,pp,planDisc,planGST,TXNAMOUNT,TXNAMOUNT,PAYMENTMODE,mCLientMobile,mCLientEmail,mCLientName,status,exp_date
        );
        call.enqueue(new Callback<Purchase>() {
            @Override
            public void onResponse(Call<Purchase> call, Response<Purchase> response) {

                String success = response.body().getTransaction().trim();
                if(success.equals("1")){
                    String success_plan_id = response.body().getPlan_id();
                    sessionManager.createSession(mCLientID,mCLientName,mCLientEmail,mCLientMobile,success_plan_id);
                    Intent intent =  new Intent(CheckoutActivity.this, TransactionStatus.class);
                    intent.putExtra("TxStatus",status);
                    intent.putExtra("TxAmount",TXNAMOUNT);
                    intent.putExtra("TxOrderID",ORDERID);
                    intent.putExtra("TxMode",PAYMENTMODE);
                    intent.putExtra("TxBankId",BANKTXNID);
                    intent.putExtra("TxID",TXNID);
                    intent.putExtra("Txdate",TXNDATE);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else if(success.equals("0")){
                    sessionManager.createSession(mCLientID,mCLientName,mCLientEmail,mCLientMobile,"null");
                    Intent intent =  new Intent(CheckoutActivity.this, TransactionStatus.class);
                    intent.putExtra("TxStatus",status);
                    intent.putExtra("TxAmount",TXNAMOUNT);
                    intent.putExtra("TxOrderID",ORDERID);
                    intent.putExtra("TxMode",PAYMENTMODE);
                    intent.putExtra("TxBankId",BANKTXNID);
                    intent.putExtra("TxID",TXNID);
                    intent.putExtra("Txdate",TXNDATE);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Purchase> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
