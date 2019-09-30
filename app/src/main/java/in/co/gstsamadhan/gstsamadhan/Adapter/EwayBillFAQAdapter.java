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
import in.co.gstsamadhan.gstsamadhan.model.EwayBillFAQ;

public class EwayBillFAQAdapter extends RecyclerView.Adapter<EwayBillFAQAdapter.MyViewHolder> {
    boolean TF=true;
    private Context mContext ;
    private List<EwayBillFAQ> mData ;
    RequestOptions option;

    public EwayBillFAQAdapter(Context mContext, List<EwayBillFAQ> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.eway_bill_faq_list,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;






        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Ques.setText(mData.get(position).getQue());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.content.setText(Html.fromHtml(mData.get(position).getAns(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.content.setText(Html.fromHtml(mData.get(position).getAns()));
        }




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Ques,content ;





        public MyViewHolder(View itemView) {
            super(itemView);
            Ques = itemView.findViewById(R.id.Ques);
            content = itemView.findViewById(R.id.content);



        }
    }

}

