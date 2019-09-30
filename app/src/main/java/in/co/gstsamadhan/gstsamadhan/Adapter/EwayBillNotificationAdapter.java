package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.model.EwayBillNotification;


public class EwayBillNotificationAdapter extends RecyclerView.Adapter<EwayBillNotificationAdapter.MyViewHolder> {
    boolean TF=true;
    private Context mContext ;
    private List<EwayBillNotification> mData ;
    RequestOptions option;

    public EwayBillNotificationAdapter(Context mContext, List<EwayBillNotification> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.ewaybill_notification_list_view,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;

        viewHolder.DownloadDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mData.get(viewHolder.getAdapterPosition()).getPdf_url().toString();
                //Toast.makeText(mContext, "-"+url+"-", Toast.LENGTH_SHORT).show();

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

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Notification_No.setText(mData.get(position).getNotification_no());
        holder.State.setText(mData.get(position).getCategory());
        holder.Dated.setText(mData.get(position).getDated());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.content.setText(Html.fromHtml(mData.get(position).getTitle(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.content.setText(Html.fromHtml(mData.get(position).getTitle()));
        }




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Notification_No,Dated,State,content ;
        CardView DownloadDoc;
        RelativeLayout EwayRV;





        public MyViewHolder(View itemView) {
            super(itemView);
            EwayRV = itemView.findViewById(R.id.EwayRV);
            Notification_No = itemView.findViewById(R.id.Notification_No);
            Dated = itemView.findViewById(R.id.Dated);
            content = itemView.findViewById(R.id.content);
            State = itemView.findViewById(R.id.State);
            DownloadDoc = itemView.findViewById(R.id.DownloadDoc);



        }
    }

}

