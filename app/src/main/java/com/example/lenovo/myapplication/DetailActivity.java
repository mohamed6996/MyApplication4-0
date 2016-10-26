package com.example.lenovo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
TextView overView, releaseDate, voteAvg;
    ImageView image_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        overView = (TextView) findViewById(R.id.over_view);
        releaseDate = (TextView) findViewById(R.id.release_date);
        voteAvg = (TextView) findViewById(R.id.vote_average);

        image_path = (ImageView) findViewById(R.id.img);
        String full_image =  Constants.IMG_BASE +getIntent().getStringExtra("IMAGE_KEY");


        overView.setText(getIntent().getStringExtra("OVER_VIEW_KEY"));
        releaseDate.setText(getIntent().getStringExtra("RELEASE_DATE_KEY"));
        voteAvg.setText(getIntent().getStringExtra("VOTE_AVERAGE_KEY"));
        Glide.with(this).load(full_image).into(image_path);




    }
}
