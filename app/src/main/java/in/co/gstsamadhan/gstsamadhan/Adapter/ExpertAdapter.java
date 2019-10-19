package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.model.Experts;

public class ExpertAdapter extends RecyclerView.Adapter<ExpertAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Experts> mData ;
    RequestOptions option;


    public ExpertAdapter(Context mContext, List<Experts> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.experts_list_view,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.exp_name.setText(mData.get(position).getName());
        holder.exp_designation.setText(mData.get(position).getDesignation());
        Glide.with(mContext).load(mData.get(position).getImage()).apply(option).into(holder.circleImageView);
        if(mData.get(position).getIsPremium().equals("1")){
            holder.premium.setVisibility(View.VISIBLE);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.exp_experties.setText(Html.fromHtml(mData.get(position).getExpertise(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.exp_experties.setText(Html.fromHtml(mData.get(position).getExpertise()));
        }




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exp_name ;
        TextView exp_designation;
        TextView exp_experties;
        CircleImageView circleImageView;
        ImageView premium;




        public MyViewHolder(View itemView) {
            super(itemView);
            premium = itemView.findViewById(R.id.premium);
            circleImageView = itemView.findViewById(R.id.profile_image);
            exp_name = itemView.findViewById(R.id.expert_name);
            exp_designation = itemView.findViewById(R.id.expert_designation);
            exp_experties = itemView.findViewById(R.id.exp_exp);



        }
    }

}
