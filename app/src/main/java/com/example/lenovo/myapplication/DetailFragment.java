package com.example.lenovo.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    TextView overView, releaseDate, voteAvg;
    ImageView image_path;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        overView = (TextView) view.findViewById(R.id.over_view);
        releaseDate = (TextView) view.findViewById(R.id.release_date);
        voteAvg = (TextView) view.findViewById(R.id.vote_average);

        image_path = (ImageView) view.findViewById(R.id.img);
        String full_image = Constants.IMG_BASE + getActivity().getIntent().getStringExtra("IMAGE_KEY");


        overView.setText(getActivity().getIntent().getStringExtra("OVER_VIEW_KEY"));
        releaseDate.setText(getActivity().getIntent().getStringExtra("RELEASE_DATE_KEY"));
        voteAvg.setText(getActivity().getIntent().getStringExtra("VOTE_AVERAGE_KEY"));
        Glide.with(this).load(full_image).into(image_path);

        return view;
    }


    /* overView = (TextView) findViewById(R.id.over_view);
        releaseDate = (TextView) findViewById(R.id.release_date);
        voteAvg = (TextView) findViewById(R.id.vote_average);

        image_path = (ImageView) findViewById(R.id.img);
        String full_image = Constants.IMG_BASE + getIntent().getStringExtra("IMAGE_KEY");


        overView.setText(getIntent().getStringExtra("OVER_VIEW_KEY"));
        releaseDate.setText(getIntent().getStringExtra("RELEASE_DATE_KEY"));
        voteAvg.setText(getIntent().getStringExtra("VOTE_AVERAGE_KEY"));
        Glide.with(this).load(full_image).into(image_path);
*/
}
