package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.MainActivity;
import in.co.gstsamadhan.gstsamadhan.R;

import in.co.gstsamadhan.gstsamadhan.model.SgstSites;

public class SgstSitesAdapter extends RecyclerView.Adapter<SgstSitesAdapter.MyViewHolder> {
    boolean TF = true;
    private Context mContext;
    private List<SgstSites> mData;
    RequestOptions option;

    public SgstSitesAdapter(Context mContext, List<SgstSites> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.state_sites_list_view, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.StateName.setText(mData.get(position).getState());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                /*Intent launchArtifactAuthor = new Intent(Intent.parseUri(ListOfArtifacts.get(position).getURL(), Intent.URI_INTENT_SCHEME));
                context.startActivity(launchArtifactAuthor);*/
                    CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

                    // Begin customizing
                    // set toolbar colors
                    intentBuilder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                    intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

                    // set start and exit animations
                    intentBuilder.setStartAnimations(mContext, android.R.anim.slide_out_right, android.R.anim.fade_in);
                    intentBuilder.setExitAnimations(mContext, android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right);

                    // build custom tabs intent
                    CustomTabsIntent customTabsIntent = intentBuilder.build();
                    customTabsIntent.launchUrl(mContext, Uri.parse(mData.get(position).getUrl()));


                }catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();}

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView StateName;
        LinearLayout layout;


        public MyViewHolder(View itemView) {
            super(itemView);
            StateName = itemView.findViewById(R.id.StateName);
            layout = itemView.findViewById(R.id.StateLayout);
        }
    }
}