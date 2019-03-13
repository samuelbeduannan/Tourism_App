package com.example.t00581897.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Samerica extends AppCompatActivity {

    Integer [] images = {R.drawable.copacobana, R.drawable.machu, R.drawable.iguazu, R.drawable.christ, R.drawable.gold, R.drawable.lencios, R.drawable.sugarloaf, R.drawable.castillo, R.drawable.monserrate, R.drawable.torres};
    static int i = 0;
    String [] description;

    Button prev, next;
    ImageSwitcher imageSwitcher;
    TextView textView;

    TextView title;

    String atitle[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samerica);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        textView = (TextView) findViewById(R.id.textView);
        description = getResources().getStringArray(R.array.samerica);

        title = (TextView) findViewById(R.id.textView7) ;

        atitle = getResources().getStringArray(R.array.samertitles);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });

        final Animation in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
        final Animation out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);


        imageSwitcher.setImageResource(images[i]);
        textView.setText(description[i]);
        textView.setMovementMethod(new ScrollingMovementMethod());
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

    public void smlocation (View view)
    {
        Intent intent = new Intent(getBaseContext(), SamericaMapsActivity.class);
        int data = i;
        intent.putExtra("intData", data);
        startActivity(intent);

    }
}
