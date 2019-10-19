package in.co.gstsamadhan.gstsamadhan;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.HashMap;
import java.util.List;

import in.co.gstsamadhan.gstsamadhan.Adapter.ActsAdapter;
import in.co.gstsamadhan.gstsamadhan.Adapter.NewsSliderAdapter;
import in.co.gstsamadhan.gstsamadhan.Login.LoginActivity;
import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;
import in.co.gstsamadhan.gstsamadhan.model.Acts;
import in.co.gstsamadhan.gstsamadhan.model.News;
import network.GstSamadhanApi;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    SessionManager sessionManager;
    ViewPager slideViewPager;
    private NewsSliderAdapter sliderAdapter;
    GstSamadhanApi gstSamadhanApi;
    private TextView[] mDots;
    private LinearLayout mdotsLayout;
    ShimmerFrameLayout shimmer_view_container;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        final GridLayout G1 = (GridLayout)view.findViewById(R.id.Gridview);
        final GridLayout G2 = (GridLayout)view.findViewById(R.id.Gridview2);
        final GridLayout G3 = view.findViewById(R.id.Gridview3);
        final ImageView arrowImageView = view.findViewById(R.id.arrowImageView);
        final ImageView arrowImageView2 = view.findViewById(R.id.arrowImageView2);
        slideViewPager = view.findViewById(R.id.slideViewPager);
        shimmer_view_container = view.findViewById(R.id.shimmer_view_container);
        mdotsLayout = view.findViewById(R.id.dotsLayout);
        sessionManager = new SessionManager(getContext());   //SessionManager Declare
        arrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G2.setVisibility(View.VISIBLE);
                arrowImageView.setVisibility(View.GONE);
                arrowImageView2.setVisibility(View.VISIBLE);
            }
        });
        arrowImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G2.setVisibility(View.GONE);
                arrowImageView.setVisibility(View.VISIBLE);
                arrowImageView2.setVisibility(View.GONE);

            }
        });

        //TODO Calling GridView for Onclick Function
        setSingleEvent(G1);
        setSingleEvent2(G2);
        setSingleEvent3(G3);
        shimmer_view_container.startShimmerAnimation();
        slideViewPager.setOnPageChangeListener(viewlistner);
        retrive();
        return view;
    }
    ViewPager.OnPageChangeListener viewlistner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public void customeTab(String URL) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        builder.addDefaultShareMenuItem();

        CustomTabsIntent anotherCustomTab = new CustomTabsIntent.Builder().build();


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_gst_samadhan_app_favicon);
        int requestCode = 100;
        Intent intent = anotherCustomTab.intent;
        intent.setData(Uri.parse(URL));

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setActionButton(bitmap, "Android", pendingIntent, true);
        builder.setShowTitle(true);


        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(URL));
    }

    public void addDotIndicator(int position){
        mDots = new TextView[5];
        mdotsLayout.removeAllViews();
        for(int i=0;i<mDots.length;i++)
        {
            mDots[i] = new TextView(getContext());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(25);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            mdotsLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
    public void retrive(){
        gstSamadhanApi = RetrofitClient.getApiClient().create(GstSamadhanApi.class);
        Call<List<News>> call = gstSamadhanApi.getFiveNews();

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> retrievedList = response.body();
                sliderAdapter = new NewsSliderAdapter(getContext(),retrievedList) ;
                slideViewPager.setAdapter(sliderAdapter);
                addDotIndicator(0);
                shimmer_view_container.setVisibility(View.GONE);
                shimmer_view_container.stopShimmerAnimation();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
    //GridLayout child RelativeLayout OnClick Function
    private void setSingleEvent(GridLayout mainGrid) {
        for (int i=0;i<mainGrid.getChildCount();i++)
        {
            RelativeLayout rv = (RelativeLayout) mainGrid.getChildAt(i);
            final int finalI = i;
            rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0)
                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){

                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{

                                Intent i = new Intent(getContext(),ActsActivity.class);
                                startActivity(i);

                            }

                        }
                    else
                        startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    else if(finalI==1)
                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),RulesActivity.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    else if(finalI==2)

                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),GstNotifications.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    else if(finalI==3)

                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),OrdersActivity.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    else if(finalI==4)

                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else if(mClientPlan.equals("1")){
                                Toast.makeText(getContext(), "Please Upgrade Your Plan", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent i = new Intent(getContext(),AdvanceRulingActivity.class);
                                startActivity(i);

                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    else if(finalI==5)
                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),PressReleaseActivity.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    else if(finalI==6)
                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),ComingSoon.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    else if(finalI==7)
                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),GstForms.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }


                }
            });




        }
    }
    //GridLayout child RelativeLayout OnClick Function
    private void setSingleEvent2(GridLayout mainGrid) {
        for (int i=0;i<mainGrid.getChildCount();i++)
        {
            RelativeLayout rv = (RelativeLayout) mainGrid.getChildAt(i);
            final int finalI = i;
            rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0){
                        customeTab("https://cbic-gst.gov.in/gst-goods-services-rates.html");
                    }
                    if(finalI==2)
                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),EwayBillNotificationActivity.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(), LoginActivity.class));

                    }
                    if(finalI==3)
                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("null")){
                                Intent i = new Intent(getContext(),PricingActivity.class);
                                i.putExtra("error","Not-Purchased");
                                startActivity(i);

                            }
                            else{
                                Intent i = new Intent(getContext(),EwayBillFaq.class);
                                startActivity(i);
                            }
                        }
                        else
                            startActivity(new Intent(getContext(),LoginActivity.class));

                    }
                    if(finalI==4)
                    {
                        startActivity(new Intent(getContext(),NewsActivity.class));

                    }
                    if(finalI==5)
                    {
                        startActivity(new Intent(getContext(),VideoLibrary.class));

                    }
                    if(finalI==6)
                    {
                        startActivity(new Intent(getContext(),GST_Sites.class));

                    }

                    if(finalI==7)
                    {
                        startActivity(new Intent(getContext(),ExpertsActivity.class));

                    }


                }
            });




        }
    }
    private void setSingleEvent3(GridLayout mainGrid) {
        for (int i=0;i<mainGrid.getChildCount();i++)
        {
            RelativeLayout rv = (RelativeLayout) mainGrid.getChildAt(i);
            final int finalI = i;
            rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0)
                    {
                        startActivity(new Intent(getContext(),ExpertsActivity.class));

                    }if(finalI==1)
                    {
                        customeTab("https://www.instamojo.com/gstsamadhan/");

                    }if(finalI==2)
                    {
                        customeTab("https://gstsamadhan.co.in/lms/");

                    }if(finalI==3)
                    {
                        startActivity(new Intent(getContext(),ComingSoon.class));

                    }
                    if(finalI==4)
                    {
                        startActivity(new Intent(getContext(),ComingSoon.class));

                    }
                    if(finalI==5)
                    {
                        startActivity(new Intent(getContext(),ComingSoon.class));

                    }
                    if(finalI==6)
                    {
                        startActivity(new Intent(getContext(),ComingSoon.class));

                    }
                    if(finalI==7)
                    {
                        startActivity(new Intent(getContext(),ComingSoon.class));

                    }


                }
            });




        }
    }



    


}