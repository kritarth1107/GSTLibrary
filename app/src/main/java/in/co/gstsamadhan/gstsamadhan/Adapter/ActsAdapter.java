package in.co.gstsamadhan.gstsamadhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.co.gstsamadhan.gstsamadhan.R;
import in.co.gstsamadhan.gstsamadhan.model.Acts;

public class ActsAdapter extends RecyclerView.Adapter<ActsAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Acts> mData ;


    public ActsAdapter(Context mContext, List<Acts> mData) {
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
/*
                Intent i = new Intent(mContext, ActsSectionActivity.class);
                i.putExtra("section_tittle",mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("section_number",mData.get(viewHolder.getAdapterPosition()).getSection());
                i.putExtra("subcatid",mData.get(viewHolder.getAdapterPosition()).getSubcatid());
                i.putExtra("year", mData.get(viewHolder.getAdapterPosition()).getYear());
                i.putExtra("chapter", mData.get(viewHolder.getAdapterPosition()).getChapter());
                mContext.startActivity(i); */
                Toast.makeText(mContext, mData.get(viewHolder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_section.setText("Section "+mData.get(position).getSection());
        holder.tv_gst.setText(mData.get(position).getSubcatid());
        holder.tv_year.setText(mData.get(position).getYear());





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




        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.SectionCardView);
            tv_title = itemView.findViewById(R.id.SectionTitleTextView);
            tv_section = itemView.findViewById(R.id.SectionNoTextView);
            tv_gst = itemView.findViewById(R.id.SectionGstTextView);
            tv_year = itemView.findViewById(R.id.SectionYearTextView);



        }
    }

}
