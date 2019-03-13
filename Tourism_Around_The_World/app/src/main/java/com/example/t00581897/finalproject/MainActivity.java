package com.example.t00581897.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity{

    GridLayout mainGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.main_Grid);

        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid)
    {

        for(int i = 0; i<mainGrid.getChildCount(); i++)
        {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener ()
            {

               public void onClick(View view)
                {
                    if(finalI == 0)
                    {

                        Intent intent = new Intent(MainActivity.this, Africa.class);
                        startActivity(intent);
                    }

                    if(finalI == 1)
                    {
                        Intent intent = new Intent(MainActivity.this, Asia.class);
                        startActivity(intent);

                    }

                    if(finalI == 2)
                    {
                        Intent intent = new Intent(MainActivity.this, Australia.class);
                        startActivity(intent);

                    }

                    if(finalI == 3)
                    {
                        Intent intent = new Intent(MainActivity.this, Europe.class);
                        startActivity(intent);

                    }

                    if(finalI == 4)
                    {
                        Intent intent = new Intent(MainActivity.this, Namerica.class);
                        startActivity(intent);}

                    if(finalI == 5)
                    {
                        Intent intent = new Intent(MainActivity.this, Samerica.class);
                        startActivity(intent);
                    }
                }
            });

        }
    }




}
