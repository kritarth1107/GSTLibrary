package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.model.PressRelease;
import in.co.gstsamadhan.gstsamadhan.model.gstforms;


public class PressReleaseAdapter extends RecyclerView.Adapter<PressReleaseAdapter.MyViewHolder> {

    private Context mContext;
    private List<PressRelease> mData;
    RequestOptions option;

    public PressReleaseAdapter(Context mContext, List<PressRelease> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.press_release_list_view, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.NotiDate.setText(mData.get(position).getDated());
        holder.NotiNumber.setText(mData.get(position).getCategory());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.content.setText(Html.fromHtml(mData.get(position).getDescription(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.content.setText(Html.fromHtml(mData.get(position).getDescription()));
        }

        holder.text1.setText("English");
        holder.text2.setText("Hindi");

        holder.EnglishDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mData.get(position).getAtachment();
                if (url.endsWith(".pdf")) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        mContext.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        Toast.makeText(mContext, "You Don't Have PDF Viewer Installed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(mContext, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.HindiDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mData.get(position).getHindilink();
                if (url.endsWith(".pdf")) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        mContext.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        Toast.makeText(mContext, "You Don't Have PDF Viewer Installed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(mContext, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView NotiNumber, NotiDate, content,text1,text2;
        CardView EnglishDownload, HindiDownload;


        public MyViewHolder(View itemView) {
            super(itemView);
            NotiNumber = itemView.findViewById(R.id.NotiNumber);
            NotiDate = itemView.findViewById(R.id.NotiDate);
            content = itemView.findViewById(R.id.content);
            EnglishDownload = itemView.findViewById(R.id.EnglishDownload);
            HindiDownload = itemView.findViewById(R.id.HindiDownload);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);

        }
    }
}