package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.ActsSection;
import in.co.gstsamadhan.gstsamadhan.NewsSection;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.model.Acts;
import in.co.gstsamadhan.gstsamadhan.model.News;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext ;
    private List<News> mData ;
    RequestOptions option;

    public NewsAdapter(Context mContext, List<News> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.news_card_view,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, NewsSection.class);
                i.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("hindi_title",mData.get(viewHolder.getAdapterPosition()).getHindiTitle());
                i.putExtra("title",mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("slug",mData.get(viewHolder.getAdapterPosition()).getSlug());
                i.putExtra("thumbnail",mData.get(viewHolder.getAdapterPosition()).getThumbnail());
                i.putExtra("author",mData.get(viewHolder.getAdapterPosition()).getAuthor());
                i.putExtra("attachment",mData.get(viewHolder.getAdapterPosition()).getAttachment());
                i.putExtra("dated",mData.get(viewHolder.getAdapterPosition()).getDated());
                i.putExtra("tags",mData.get(viewHolder.getAdapterPosition()).getTags());
                i.putExtra("content",mData.get(viewHolder.getAdapterPosition()).getContent());
                i.putExtra("created_at",mData.get(viewHolder.getAdapterPosition()).getCreatedAt());
                i.putExtra("updated_at",mData.get(viewHolder.getAdapterPosition()).getUpdatedAt());
                i.putExtra("status",mData.get(viewHolder.getAdapterPosition()).getStatus());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));
        holder.newsDated.setText(mData.get(position).getDated());
        holder.newsAuthor.setText(mData.get(position).getAuthor());
        Glide.with(mContext).load(mData.get(position).getThumbnail()).apply(option).into(holder.thumbnail);
        String hCheck = mData.get(position).getHindiTitle();
        if(hCheck.equals("")){
            holder.tv_title.setText(mData.get(position).getTitle());
        }
        else {
            holder.tv_title.setText(mData.get(position).getHindiTitle());
        }



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title ;
        TextView newsDated;
        TextView newsAuthor;
        LinearLayout container;
        ImageView thumbnail;




        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            container = itemView.findViewById(R.id.container);
            tv_title = itemView.findViewById(R.id.newsTitle);
            newsDated = itemView.findViewById(R.id.newsDated);
            newsAuthor = itemView.findViewById(R.id.newsAuthor);



        }
    }

}
