package in.co.gstsamadhan.gstsamadhan;

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

import in.co.gstsamadhan.gstsamadhan.Adapter.ActsAdapter;
import in.co.gstsamadhan.gstsamadhan.Adapter.NewsAdapter;
import in.co.gstsamadhan.gstsamadhan.model.Acts;
import in.co.gstsamadhan.gstsamadhan.model.News;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    ShimmerFrameLayout shimmerFrameLayout;
    private GstSamadhanApi gstSamadhanApi;
    ImageView NoInternetImage;
    LinearLayout NOaccessInternet;
    CardView RetryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        NoInternetImage = findViewById(R.id.NoInternetImage);
        RetryButton = findViewById(R.id.RetryButton);
        NOaccessInternet = findViewById(R.id.NOaccessInternet);
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        //shimmer
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);

        //recyclerView
        recyclerView = findViewById(R.id.ActsRecyclerView);


        retrieveValues("");



    }//OnCreate End

    private void retrieveValues(final String Keyword) {
        if(!isConnected(NewsActivity.this)){
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

            Call<List<News>> call = gstSamadhanApi.getNews(Keyword);

            call.enqueue(new Callback<List<News>>() {
                @Override
                public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                    List<News> retrievedList = response.body();
                    NewsAdapter newsAdapter = new NewsAdapter(getApplicationContext(),retrievedList) ;
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(newsAdapter);
                    shimmerFrameLayout.stopShimmerAnimation();
                    shimmerFrameLayout.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<List<News>> call, Throwable t) {

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
