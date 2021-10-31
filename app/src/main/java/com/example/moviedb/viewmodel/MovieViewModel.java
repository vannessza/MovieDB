package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.model.Movies;
import com.example.moviedb.model.MoviesCast;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.UpComing;
import com.example.moviedb.respositories.MovieRepository;

import retrofit2.http.Query;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = MovieRepository.getInstance();
    }
    //== Begin of viewmodel get movie by id
    private MutableLiveData<Movies> resulGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId){
        resulGetMovieById = repository.getMovieData(movieId);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return resulGetMovieById;
    }
    //== End of viewmodel get movie by id

    //==Begin of viewmodel get now playing

    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }

    //==End of viewmodel get now playing

    private MutableLiveData<MoviesCast> resultGetMoviesCast = new MutableLiveData<>();
    public void getMoviesCast(String movie){
        resultGetMoviesCast = repository.getCreditsData(movie);
    }
    public LiveData<MoviesCast> getResultMoviesCast(){
        return resultGetMoviesCast;
    }

    private MutableLiveData<UpComing> resultGetUpComing = new MutableLiveData<>();
    public void getUpComing(){
        resultGetUpComing = repository.getUpComingData();
    }
    public LiveData<UpComing> getResultUpComing(){
        return resultGetUpComing;
    }



}
