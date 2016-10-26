package com.example.lenovo.myapplication;

/**
 * Created by lenovo on 10/24/2016.
 */

public class ItemModel {

    String image_path, film_name, over_view, release_date, vote_average;



    public ItemModel(String overView, String film_name, String over_view, String releaseDate, String voreAverage) {
        this.film_name = film_name;
        this.image_path = overView;
        this.over_view = over_view;
        this.release_date = releaseDate;
        this.vote_average = voreAverage;


    }

    public String getImagePath() {
        return image_path;
    }

    public void setImagePath(String overView) {
        this.image_path = overView;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getOver_view() {
        return over_view;
    }

    public void setOver_view(String over_view) {
        this.over_view = over_view;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
