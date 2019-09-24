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

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.NewsSection;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.VideoSection;
import in.co.gstsamadhan.gstsamadhan.model.News;
import in.co.gstsamadhan.gstsamadhan.model.Video;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Video> mData ;
    RequestOptions option;

    public VideoAdapter(Context mContext, List<Video> mData) {
        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.videodemoimage).error(R.drawable.videodemoimage);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.video_list_view,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, VideoSection.class);
                i.putExtra("description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("id",mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("title",mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("slug",mData.get(viewHolder.getAdapterPosition()).getSlug());
                i.putExtra("thumbnail",mData.get(viewHolder.getAdapterPosition()).getThumbnail());
                i.putExtra("author",mData.get(viewHolder.getAdapterPosition()).getAuthor());
                i.putExtra("dated",mData.get(viewHolder.getAdapterPosition()).getDated());
                i.putExtra("tags",mData.get(viewHolder.getAdapterPosition()).getTags());
                i.putExtra("status",mData.get(viewHolder.getAdapterPosition()).getStatus());
                i.putExtra("video_url",mData.get(viewHolder.getAdapterPosition()).getVideo_url());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.date.setText(mData.get(position).getDated());
        Glide.with(mContext).load(mData.get(position).getThumbnail()).apply(option).into(holder.thumbnail);




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title ;
        TextView date;
        LinearLayout container;
        ImageView thumbnail;




        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.VideoThumbnail);
            container = itemView.findViewById(R.id.videoCard);
            tv_title = itemView.findViewById(R.id.videoTitle);
            date = itemView.findViewById(R.id.videoDate);



        }
    }

}