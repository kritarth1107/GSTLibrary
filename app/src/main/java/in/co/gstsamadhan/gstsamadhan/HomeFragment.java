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


public class HomeFragment extends Fragment {
   
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        final GridLayout G1 = (GridLayout)view.findViewById(R.id.Gridview);
        final GridLayout G2 = (GridLayout)view.findViewById(R.id.Gridview2);
        final ImageView arrowImageView = view.findViewById(R.id.arrowImageView);
        final ImageView arrowImageView2 = view.findViewById(R.id.arrowImageView2);

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
                        Intent i = new Intent(getContext(),ActsActivity.class);
                        startActivity(i);

                    }


                }
            });




        }
    }

    
}