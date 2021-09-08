package com.example.flixtster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//this is basically a plain ole java object - encapsulates the idea of a movie
public class Movie {

    String backdropPath;
    String posterPath;
    String title;
    String overview;

    //constructor that takes in a Json object and is used to construct a moving object
    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    //creating a static method here to make a list of movies
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

//we right clicked->generate getters-> then created getters for all three fields
    public String getPosterPath() {

        //to do this properly what you should be
        //doing is actually fetching all of the
        //available sizes appending down at the
        //base URL that you have back and then
        //adding in the relative path
        //what we're doing is adding %s and hardcoding the width in

        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath); //%s is "here's what I want to replace w/ posterpath"
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
