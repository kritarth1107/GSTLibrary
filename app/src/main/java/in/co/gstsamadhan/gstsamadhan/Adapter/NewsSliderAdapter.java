package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.NewsSection;
import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.model.Acts;
import in.co.gstsamadhan.gstsamadhan.model.News;

public class NewsSliderAdapter extends PagerAdapter {


    Context context;
    private List<News> mData ;
    LayoutInflater layoutInflater;
    RequestOptions option;

    public NewsSliderAdapter(Context context,List<News> mData) {
        this.context = context;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;

    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container,final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.news_slider,container,false);
        CardView news_card = view.findViewById(R.id.news_card);
        ImageView slideimageview = (ImageView) view.findViewById(R.id.news_thumbnail);
        TextView slide_heading = (TextView) view.findViewById(R.id.news_heading);
        TextView slide_date = (TextView) view.findViewById(R.id.news_date);
        TextView slide_author = (TextView) view.findViewById(R.id.news_author);
        String hCheck = mData.get(position).getHindiTitle();
        if(hCheck.equals("") || hCheck.startsWith("\t")){
            slide_heading.setText(mData.get(position).getTitle());
        }
        else {
            slide_heading.setText(mData.get(position).getHindiTitle());
        }
        Glide.with(context).load(mData.get(position).getThumbnail()).apply(option).into(slideimageview);
        slide_date.setText(mData.get(position).getDated());
        slide_author.setText(mData.get(position).getAuthor());

        news_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, NewsSection.class);
                i.putExtra("id",mData.get(position).getId());
                i.putExtra("hindi_title",mData.get(position).getHindiTitle());
                i.putExtra("title",mData.get(position).getTitle());
                i.putExtra("slug",mData.get(position).getSlug());
                i.putExtra("thumbnail",mData.get(position).getThumbnail());
                i.putExtra("author",mData.get(position).getAuthor());
                i.putExtra("attachment",mData.get(position).getAttachment());
                i.putExtra("dated",mData.get(position).getDated());
                i.putExtra("tags",mData.get(position).getTags());
                i.putExtra("content",mData.get(position).getContent());
                i.putExtra("created_at",mData.get(position).getCreatedAt());
                i.putExtra("updated_at",mData.get(position).getUpdatedAt());
                i.putExtra("status",mData.get(position).getStatus());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
