package in.co.gstsamadhan.gstsamadhan;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;

import in.co.gstsamadhan.gstsamadhan.Session.SessionManager;


public class HomeFragment extends Fragment {
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        final GridLayout G1 = (GridLayout)view.findViewById(R.id.Gridview);
        final GridLayout G2 = (GridLayout)view.findViewById(R.id.Gridview2);
        final ImageView arrowImageView = view.findViewById(R.id.arrowImageView);
        final ImageView arrowImageView2 = view.findViewById(R.id.arrowImageView2);
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

        return view;
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
                        Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

                    }
                    else if(finalI==4)

                    {
                        if(sessionManager.isLoggin())
                        {
                            HashMap<String, String> user = sessionManager.getUserDetail();
                            String mClientPlan = user.get(sessionManager.CLIENT_PLAN);
                            if(mClientPlan.equals("2") || mClientPlan.equals("3")){
                                Intent i = new Intent(getContext(),AdvanceRulingActivity.class);
                                startActivity(i);

                            }
                            else{

                                Toast.makeText(getContext(), "Please Upgrade Your Plan", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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
                            Toast.makeText(getContext(), "Please Login to Access", Toast.LENGTH_SHORT).show();

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


                }
            });




        }
    }



    


}