package in.co.gstsamadhan.gstsamadhan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class Register_Step_2 extends AppCompatActivity {
    LinearLayout accordian;
    ImageView Up,Down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__step_2);
        Up = findViewById(R.id.Up_Ind);
        Down = findViewById(R.id.Down_Ind);
        accordian = findViewById(R.id.Accordion_Body);

        Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Up.setVisibility(View.GONE);
                Down.setVisibility(View.VISIBLE);
                accordian.setVisibility(View.GONE);
                accordian.setAnimation(AnimationUtils.loadAnimation(Register_Step_2.this,R.anim.fade_down_up));
            }
        });
        Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Up.setVisibility(View.VISIBLE);
                Down.setVisibility(View.GONE);
                accordian.setVisibility(View.VISIBLE);
                accordian.setAnimation(AnimationUtils.loadAnimation(Register_Step_2.this,R.anim.fade_up_down));
            }
        });


    }
}
