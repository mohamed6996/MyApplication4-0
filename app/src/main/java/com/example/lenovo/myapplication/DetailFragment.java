package com.example.lenovo.myapplication;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    TextView overView, releaseDate, voteAvg , review_field ;
    ImageView image_path;
    Button trailer, review;
    String trailer_id, key;
    String review_content ;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        overView = (TextView) view.findViewById(R.id.over_view);
        releaseDate = (TextView) view.findViewById(R.id.release_date);
        voteAvg = (TextView) view.findViewById(R.id.vote_average);
        image_path = (ImageView) view.findViewById(R.id.img);
        trailer = (Button) view.findViewById(R.id.trailer);
        review_field = (TextView) view.findViewById(R.id.review_field);

        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder builder = new StringBuilder();
                builder.append(" https://api.themoviedb.org/3/movie/");
                builder.append(trailer_id);
                builder.append("/videos?api_key=1a618051961d7a730414257885f0d9d3&language=en-US");
                String Full_id = builder.toString();

                fetch_youtube_key(Full_id);


                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + key));
                    startActivity(intent);
                }

            }
        });


        review = (Button) view.findViewById(R.id.review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder builder = new StringBuilder();
                builder.append(" https://api.themoviedb.org/3/movie/");
                builder.append(trailer_id);
                builder.append("/reviews?api_key=1a618051961d7a730414257885f0d9d3&language=en-US");
                String Full_id = builder.toString();

                fetch_review(Full_id);
                review_field.setText(review_content);

            }
        });


        if (MainActivity.mTWO_PANE) {
            Bundle bundle = getArguments();
            overView.setText(bundle.getString("OVER_VIEW_KEY"));
            releaseDate.setText(bundle.getString("RELEASE_DATE_KEY"));
            voteAvg.setText(bundle.getString("VOTE_AVERAGE_KEY"));

            String full_image = Constants.IMG_BASE + bundle.getString("IMAGE_KEY");
            Glide.with(this).load(full_image).placeholder(R.drawable.ic_dots).into(image_path);

            trailer_id = bundle.getString("ID_KEY");


        } else {
            String full_image = Constants.IMG_BASE + getActivity().getIntent().getStringExtra("IMAGE_KEY");

            overView.setText(getActivity().getIntent().getStringExtra("OVER_VIEW_KEY"));
            releaseDate.setText(getActivity().getIntent().getStringExtra("RELEASE_DATE_KEY"));
            voteAvg.setText(getActivity().getIntent().getStringExtra("VOTE_AVERAGE_KEY"));
            Glide.with(this).load(full_image).placeholder(R.drawable.ic_dots).into(image_path);

            trailer_id = getActivity().getIntent().getStringExtra("ID_KEY");
        }


        return view;
    }

    public void fetch_youtube_key(String id) {


        StringRequest string = new StringRequest(Request.Method.GET, id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    JSONObject first = array.getJSONObject(0);
                    key = first.getString("key");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(string);

    }

    public void fetch_review(String id) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    JSONObject first = array.getJSONObject(0);
                    review_content = first.getString("content");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }


}
