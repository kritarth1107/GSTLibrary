package in.co.gstsamadhan.gstsamadhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.Display;
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
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    boolean boolean_save;
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
                                bitmap = loadBitmapFromView(SectionLL, SectionLL.getWidth(), SectionLL.getHeight());
                                createPdf(section);
                                break;
                            case R.id.print_bottom_navigation:
                                break;
                            case R.id.share_bottom_navigation:
                                break;

                        }
                        return true;
                    }
                });

        fn_permission();



    }

        private void createPdf(String section){
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics displaymetrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            float hight = displaymetrics.heightPixels ;
            float width = displaymetrics.widthPixels ;

            int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();


            Paint paint = new Paint();
            canvas.drawPaint(paint);


            bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

            paint.setColor(Color.BLUE);
            canvas.drawBitmap(bitmap, 0, 0 , null);
            document.finishPage(page);


            // write the document content
            String dir = Environment.getExternalStorageDirectory().getPath();
            String targetPdf = dir+"/GstSamadhan";
            File filePath = new File(targetPdf);
            try {
                document.writeTo(new FileOutputStream(filePath));
                boolean_save=true;
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            }
            document.close();
        }



        public static Bitmap loadBitmapFromView(View v, int width, int height) {
            Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.draw(c);

            return b;
        }

        private void fn_permission() {
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                    (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

                if ((ActivityCompat.shouldShowRequestPermissionRationale(ActsSection.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
                } else {
                    ActivityCompat.requestPermissions(ActsSection.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_PERMISSIONS);

                }

                if ((ActivityCompat.shouldShowRequestPermissionRationale(ActsSection.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
                } else {
                    ActivityCompat.requestPermissions(ActsSection.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSIONS);

                }
            } else {
                boolean_permission = true;
}
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == REQUEST_PERMISSIONS) {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    boolean_permission = true;


                } else {
                    Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

                }
            }
        }






}
