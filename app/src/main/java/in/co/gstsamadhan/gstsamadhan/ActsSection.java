package in.co.gstsamadhan.gstsamadhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ActsSection extends AppCompatActivity {
    Intent i;
    TextView Chapter,Section,Title,Content;
    Bitmap bitmap;
    LinearLayout SectionLL;
    FrameLayout design_bottom_sheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acts_section);

        //TODO- PREVENT SCREENSHOT AND SCREEN RECORDING
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        }
        SectionLL = findViewById(R.id.SectionLL);
        //getIntent
        i=getIntent();
        final String id = i.getStringExtra("id");
        final String serial = i.getStringExtra("serial");
        final String title = i.getStringExtra("title");
        final String catid = i.getStringExtra("catid");
        final String subcatid = i.getStringExtra("subcatid");
        final String section = i.getStringExtra("section");
        final String chapter = i.getStringExtra("chapter");
        final String keynote = i.getStringExtra("keynote");
        final String releventrule = i.getStringExtra("releventrule");
        final String content = i.getStringExtra("content");
        final String userid = i.getStringExtra("userid");
        final String postdate = i.getStringExtra("postdate");
        final String year = i.getStringExtra("year");
        final String description = i.getStringExtra("description");
        final String actCreated = i.getStringExtra("actCreated");
        final String actUpdated = i.getStringExtra("actUpdated");
        final String status = i.getStringExtra("status");
        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        toolbar.setTitle("Section "+section+" ("+subcatid+" "+year+")");

        //TextView
        Chapter = findViewById(R.id.Chapter);
        Chapter.setText(chapter);
        Title = findViewById(R.id.Title);
        Title.setText(title);
        Section = findViewById(R.id.Section);
        Section.setText("Section : "+section);
        Content = findViewById(R.id.Content);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Content.setText(Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY));
        } else {
            Content.setText(Html.fromHtml(content));
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNVsection);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.pdf_bottom_navigation:

                                break;
                            case R.id.print_bottom_navigation:
                                break;
                            case R.id.share_bottom_navigation:
                                break;

                        }
                        return true;
                    }
                });



    }




}
