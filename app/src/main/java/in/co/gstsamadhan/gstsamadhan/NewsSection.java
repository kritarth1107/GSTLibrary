package in.co.gstsamadhan.gstsamadhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class NewsSection extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_section);
        i=getIntent();
        final String id = i.getStringExtra("id");
        final String title = i.getStringExtra("title");
        final String hindiTitle = i.getStringExtra("hindiTitle");
        final String slug = i.getStringExtra("slug");
        final String thumbnail = i.getStringExtra("thumbnail");
        final String content = i.getStringExtra("content");
        final String author = i.getStringExtra("author");
        final String attachment = i.getStringExtra("attachment");
        final String dated = i.getStringExtra("dated");
        final String tags = i.getStringExtra("tags");
        final String createdAt = i.getStringExtra("createdAt");
        final String updatedAt = i.getStringExtra("updatedAt");
        final String status = i.getStringExtra("status");

        Toolbar toolbar= findViewById(R.id.toolbarNEWSsection);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);

        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_content = findViewById(R.id.aa_content);
        TextView tv_name = findViewById(R.id.aa_news_title);
        TextView tv_studio = findViewById(R.id.aa_author);
        TextView tv_categorie = findViewById(R.id.aa_dated) ;
        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view
        tv_name.setText(title);
        tv_categorie.setText(dated);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tv_content.setText(Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_content.setText(Html.fromHtml(content));
        }
        tv_studio.setText(author);

        collapsingToolbarLayout.setTitle(title);
        collapsingToolbarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.ic_broken_image);


        // set image using Glide
        Glide.with(this).load(thumbnail).apply(requestOptions).into(img);




    }
}
