package com.example.moviedb2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.moviedb2.R;
import com.example.moviedb2.helper.Const;
import com.example.moviedb2.model.Movies;
import com.example.moviedb2.model.NowPlaying;
import com.example.moviedb2.viewmodel.MovieViewModel;
import com.example.moviedb2.R;
import com.example.moviedb2.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView posterpath,backdroppath, imageview_back;
    private TextView lbl_text,lbl_title ,lbl_releasedate ,lbl_language, lbl_popularity,
            lbl_votecount, lbl_voteaverage, lbl_overview, lbl_originaltitle, lbl_genre;
    private String movie_id = "",video="",poster_path="",backdrop_path="", overview="", release_date="",
            title="", leanguage="", popularity= "",vote_count="",vote_average="", original_title="";
    private MovieViewModel view_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        poster_path = intent.getStringExtra("movie_poster");
        backdrop_path = intent.getStringExtra("movie_backdrop");
        title = intent.getStringExtra("movie_title");
        release_date = intent.getStringExtra("movie_releasedate");
        overview = intent.getStringExtra("movie_overview");
        leanguage = intent.getStringExtra("movie_leanguage");
        popularity = intent.getStringExtra("movie_popularity");
        vote_count = intent.getStringExtra("movie_vote");
        vote_average = intent.getStringExtra("movie_average");
        original_title = intent.getStringExtra("movie_originaltitle");


        lbl_text = findViewById(R.id.lbl_movie_details);
        posterpath = findViewById(R.id.posterpath_movie_details);
        backdroppath = findViewById(R.id.backdrop_path_movie_details);
        imageview_back = findViewById(R.id.imageview_back_movie_details);
        lbl_title = findViewById(R.id.lbl_title_movie_details);
        lbl_releasedate = findViewById(R.id.releasedate_movie_details);
        lbl_language = findViewById(R.id.lbl_language_movie_details);
        lbl_popularity = findViewById(R.id.lbl_popularity_movie_details);
        lbl_votecount = findViewById(R.id.lbl_votecount_movie_details);
        lbl_voteaverage = findViewById(R.id.lbl_voteaverage_movie_details);
        lbl_overview = findViewById(R.id.lbl_overview_movie_details);
        lbl_originaltitle = findViewById(R.id.lbl_originaltitle_movie_details);
        lbl_genre = findViewById(R.id.lbl_genre_movie_details);

        String img_path = Const.IMG_URL + poster_path;
        Glide.with(MovieDetailsActivity.this).load(img_path).into(posterpath);

        String img_backdrop = Const.IMG_URL + backdrop_path;
        Glide.with(MovieDetailsActivity.this).load(img_backdrop).into(backdroppath);


        lbl_text.setText(movie_id);
        lbl_title.setText(title);
        lbl_releasedate.setText(release_date);
        lbl_overview.setText(overview);
        lbl_language.setText(leanguage);
        lbl_popularity.setText(popularity);
        lbl_votecount.setText(vote_count);
        lbl_voteaverage.setText(vote_average);
        lbl_originaltitle.setText(original_title);

        view_model = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        view_model.getMovieById(movie_id);
        view_model.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovies);
    }

    private Observer<Movies> showResultMovies = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String genre="";
            for (int i = 0; i<movies.getGenres().size(); i++){
                genre += movies.getGenres().get(i).getName();
                if(i == movies.getGenres().size()-1){

                }else{
                    genre +=", ";
                }
            }
            lbl_genre.setText(genre);
        }
    };
    @Override
    public void onBackPressed() {
        finish();
    }
}