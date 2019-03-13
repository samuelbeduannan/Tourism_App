package com.example.t00581897.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Africa extends AppCompatActivity {

    Integer [] images = {R.drawable.mt, R.drawable.kruger, R.drawable.vic, R.drawable.great_pyramid, R.drawable.sossusvlei, R.drawable.botanical, R.drawable.diani, R.drawable.waterfront, R.drawable.cradle, R.drawable.archi};
   static int i = 0;
    String [] description;
    int countImage = images.length;
    int currentImage = -1;

    Button prev, next;
    ImageSwitcher imageSwitcher;
    TextView textView;

    TextView title;

    String atitle[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_africa);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        textView = (TextView) findViewById(R.id.textView);
        description = getResources().getStringArray(R.array.africa);
        title = (TextView) findViewById(R.id.textView2) ;

        atitle = getResources().getStringArray(R.array.afrotitles);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });

        //final Animation inRightToCenter = AnimationUtils.loadAnimation(this, R.anim.img_switch_in_rc);
        //final Animation outCenterToLeft = AnimationUtils.loadAnimation(this, R.anim.img_switch_out_cl);

        //final Animation inLeftToCenter = AnimationUtils.loadAnimation(this, R.anim.img_switch_in_lc);
        //final Animation outCenterToRight = AnimationUtils.loadAnimation(this, R.anim.img_switch_out_cr);

        final Animation in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
        final Animation out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);



        imageSwitcher.setImageResource(images[i]);
        textView.setText(description[i]);
        title.setText(atitle[i]);

        prev = (Button) findViewById(R.id.p_prev);
        next = (Button) findViewById(R.id.p_next);

        prev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                imageSwitcher.setInAnimation(out);

                if(i > 0)
                {
                    i--;
                    imageSwitcher.setImageResource(images[i]);
                    textView.setText(description[i]);
                    title.setText(atitle[i]);


                }


            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                imageSwitcher.setInAnimation(in);


                if(i < images.length - 1)
                {
                    i++;
                    imageSwitcher.setImageResource(images[i]);
                    textView.setText(description[i]);
                    title.setText(atitle[i]);

                }



            }
        });
    }



    public void location (View view)
    {
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        int data = i;
        intent.putExtra("intData", data);
        startActivity(intent);

    }






}
