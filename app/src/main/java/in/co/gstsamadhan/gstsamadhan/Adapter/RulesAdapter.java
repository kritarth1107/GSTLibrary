package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.ActsSection;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.RulesSection;
import in.co.gstsamadhan.gstsamadhan.model.Rules;

public class RulesAdapter extends RecyclerView.Adapter<RulesAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Rules> mData ;


    public RulesAdapter(Context mContext, List<Rules> mData) {
        this.mContext = mContext;
        this.mData = mData;




    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.list_card_design,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, RulesSection.class);
                i.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("title",mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("catid",mData.get(viewHolder.getAdapterPosition()).getCatid());
                i.putExtra("subcatid",mData.get(viewHolder.getAdapterPosition()).getSubcatid());
                i.putExtra("section",mData.get(viewHolder.getAdapterPosition()).getSection());
                i.putExtra("chapter",mData.get(viewHolder.getAdapterPosition()).getChapter());
                i.putExtra("keynote",mData.get(viewHolder.getAdapterPosition()).getKeynote());
                i.putExtra("content",mData.get(viewHolder.getAdapterPosition()).getContent());
                i.putExtra("userid",mData.get(viewHolder.getAdapterPosition()).getUserid());
                i.putExtra("postdate",mData.get(viewHolder.getAdapterPosition()).getPostdate());
                i.putExtra("year",mData.get(viewHolder.getAdapterPosition()).getYear());
                i.putExtra("description",mData.get(viewHolder.getAdapterPosition()).getDescription());

                i.putExtra("releventact",mData.get(viewHolder.getAdapterPosition()).getReleventact());
                i.putExtra("status",mData.get(viewHolder.getAdapterPosition()).getStatus());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.view_container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_section.setText("Section "+mData.get(position).getSection());
        holder.tv_gst.setText(mData.get(position).getSubcatid());
        holder.tv_year.setText(mData.get(position).getYear());
        holder.ImageIcon.setImageResource(R.drawable.ic_clipboard);




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title ;
        TextView tv_section;
        TextView tv_gst;
        TextView tv_year;
        CardView view_container;
        ImageView ImageIcon;



        public MyViewHolder(View itemView) {
            super(itemView);
            ImageIcon = itemView.findViewById(R.id.ImageIcon);
            view_container = itemView.findViewById(R.id.SectionCardView);
            tv_title = itemView.findViewById(R.id.SectionTitleTextView);
            tv_section = itemView.findViewById(R.id.SectionNoTextView);
            tv_gst = itemView.findViewById(R.id.SectionGstTextView);
            tv_year = itemView.findViewById(R.id.SectionYearTextView);



        }
    }

}
