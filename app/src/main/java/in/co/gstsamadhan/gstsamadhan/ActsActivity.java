package in.co.gstsamadhan.gstsamadhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.co.gstsamadhan.gstsamadhan.Adapter.ActsAdapter;
import in.co.gstsamadhan.gstsamadhan.model.Acts;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActsActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    ShimmerFrameLayout shimmerFrameLayout;
    EditText KeywordEditText;
    private GstSamadhanApi gstSamadhanApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acts);
        KeywordEditText = findViewById(R.id.KeywordEditText);

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
        shimmerFrameLayout.startShimmerAnimation();

        //recyclerView
        recyclerView = findViewById(R.id.ActsRecyclerView);
        KeywordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                retrieveValues(charSequence.toString())/*(charSequence.toString())*/;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        retrieveValues("");


    }//OnCreate End

    private void retrieveValues(String Keyword) {
        gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);

        Call<List<Acts>> call = gstSamadhanApi.getActs(Keyword);

        call.enqueue(new Callback<List<Acts>>() {
            @Override
            public void onResponse(Call<List<Acts>> call, Response<List<Acts>> response) {
                List<Acts> retrievedList = response.body();
                ActsAdapter actsAdapter = new ActsAdapter(getApplicationContext(),retrievedList) ;
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(actsAdapter);
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<Acts>> call, Throwable t) {

            }
        });

    }
}
