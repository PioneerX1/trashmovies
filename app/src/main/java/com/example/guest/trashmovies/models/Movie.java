package com.example.guest.trashmovies.models;


public class Movie {

    private String mTitle;
    private String mPosterLink;
    private String mReleaseDate;

    public Movie(String title, String posterLink, String releaseDate) {
        this.mTitle = title;
        this.mPosterLink = "https://image.tmdb.org/t/p/w640" + posterLink;
        this.mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterLink() {
        return mPosterLink;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

}
