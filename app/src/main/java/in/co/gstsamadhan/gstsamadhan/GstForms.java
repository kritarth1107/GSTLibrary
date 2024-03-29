package in.co.gstsamadhan.gstsamadhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.Adapter.GstFormsAdapter;
import in.co.gstsamadhan.gstsamadhan.Adapter.NotificationAdapter;
import in.co.gstsamadhan.gstsamadhan.model.Notification;
import in.co.gstsamadhan.gstsamadhan.model.gstforms;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GstForms extends AppCompatActivity {
    private RecyclerView recyclerView ;
    ShimmerFrameLayout shimmerFrameLayout;
    EditText KeywordEditText;
    private GstSamadhanApi gstSamadhanApi;
    ImageView NoInternetImage,filter;
    LinearLayout NOaccessInternet;
    CardView RetryButton;
    FrameLayout design_bottom_sheet;
    View bg;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst_forms);
        design_bottom_sheet = findViewById(R.id.design_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(design_bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        filter = findViewById(R.id.filter);
        KeywordEditText = findViewById(R.id.KeywordEditText);
        NoInternetImage = findViewById(R.id.NoInternetImage);
        RetryButton = findViewById(R.id.RetryButton);
        NOaccessInternet = findViewById(R.id.NOaccessInternet);
        //Toolbar
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //shimmer
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        bg = findViewById(R.id.bg);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                bg.setVisibility(View.GONE);
                toolbar.setEnabled(true);
            }
        });
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED)
                    bg.setVisibility(View.GONE);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.d(TAG, "onSlide: slideOffset" + slideOffset + "");
                bg.setVisibility(View.VISIBLE);
                bg.setAlpha(slideOffset);
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                toolbar.setEnabled(false);
                bg.setVisibility(View.VISIBLE);
            }
        });

        //recyclerView
        recyclerView = findViewById(R.id.ActsRecyclerView);
        KeywordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //retrieveValues(charSequence.toString())/*(charSequence.toString())*/;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                retrieveValues(editable.toString())/*(charSequence.toString())*/;
            }

        });
        retrieveValues("");



    }//OnCreate End

    private void retrieveValues(final String Keyword) {
        if(!isConnected(GstForms.this)){
            shimmerFrameLayout.setVisibility(View.GONE);
            NOaccessInternet.setVisibility(View.VISIBLE);
            RetryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    retrieveValues(Keyword);
                }
            });
        }
        else {
            shimmerFrameLayout.setVisibility(View.VISIBLE);
            NOaccessInternet.setVisibility(View.GONE);
            shimmerFrameLayout.startShimmerAnimation();
            gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);

            Call<List<gstforms>> call = gstSamadhanApi.getGstForms(Keyword);

            call.enqueue(new Callback<List<gstforms>>() {
                @Override
                public void onResponse(Call<List<gstforms>> call, Response<List<gstforms>> response) {
                    List<gstforms> retrievedList = response.body();
                    GstFormsAdapter gstFormsAdapter = new GstFormsAdapter(getApplicationContext(),retrievedList) ;
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(gstFormsAdapter);
                    shimmerFrameLayout.stopShimmerAnimation();
                    shimmerFrameLayout.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<List<gstforms>> call, Throwable t) {

                }
            });
        }


    }
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }
}