package in.co.gstsamadhan.gstsamadhan.Adapter;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.Register_Step_2;
import in.co.gstsamadhan.gstsamadhan.model.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    boolean TF=true;
    private Context mContext ;
    private List<Notification> mData ;
    RequestOptions option;

    public NotificationAdapter(Context mContext, List<Notification> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.noti_list_view,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.Down_Ind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.Accordion_Body.setVisibility(View.VISIBLE);
                viewHolder.Up_Ind.setVisibility(View.VISIBLE);
                viewHolder.Down_Ind.setVisibility(View.GONE);
                viewHolder.Accordion_Body.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_up_down));
            }
        });
        viewHolder.Up_Ind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.Accordion_Body.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_down_up));
                viewHolder.Accordion_Body.setVisibility(View.GONE);
                viewHolder.Down_Ind.setVisibility(View.VISIBLE);
                viewHolder.Up_Ind.setVisibility(View.GONE);

            }
        });
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.Accordion_Body.setVisibility(View.VISIBLE);
                viewHolder.Up_Ind.setVisibility(View.VISIBLE);
                viewHolder.Down_Ind.setVisibility(View.GONE);
                viewHolder.Accordion_Body.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_up_down));
            }
        });
        viewHolder.EnglishDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mData.get(viewHolder.getAdapterPosition()).getEpdfurl();
                Toast.makeText(mContext, "-"+url+"-", Toast.LENGTH_SHORT).show();
                /*
                if (url.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    try {
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        Toast.makeText(mContext, "You Don't Have PDF Viewer Installed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(mContext, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        viewHolder.HindiDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mData.get(viewHolder.getAdapterPosition()).getHpdfurl();
                Toast.makeText(mContext, "-"+url+"-", Toast.LENGTH_SHORT).show();
                /*
                if (url.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    try {
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        Toast.makeText(mContext, "You Don't Have PDF Viewer Installed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(mContext, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }*/
            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        holder.NotiDate.setText("Dated: "+mData.get(position).getnotification_date());
        holder.NotiNumber.setText(mData.get(position).getNotificationno());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.content.setText(Html.fromHtml(mData.get(position).getDescription(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.content.setText(Html.fromHtml(mData.get(position).getDescription()));
        }




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView NotiNumber,NotiDate,content ;
        CardView EnglishDownload,HindiDownload;
        LinearLayout container,Accordion_Body;
        ImageView Down_Ind,Up_Ind;




        public MyViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            NotiNumber = itemView.findViewById(R.id.NotiNumber);
            NotiDate = itemView.findViewById(R.id.NotiDate);
            content = itemView.findViewById(R.id.content);
            EnglishDownload = itemView.findViewById(R.id.EnglishDownload);
            HindiDownload = itemView.findViewById(R.id.HindiDownload);
            Down_Ind = itemView.findViewById(R.id.Down_Ind);
            Up_Ind = itemView.findViewById(R.id.Up_Ind);
            Accordion_Body = itemView.findViewById(R.id.Accordion_Body);



        }
    }

}
