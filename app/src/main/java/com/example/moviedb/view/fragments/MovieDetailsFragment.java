package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.MoviesCastAdapter;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.MoviesCast;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView lbl_movie_id;
    private ImageView posterpath,backdroppath, imageview_back;
    private TextView lbl_text,lbl_title ,lbl_releasedate ,lbl_language, lbl_popularity,
            lbl_votecount, lbl_voteaverage, lbl_overview, lbl_runtime, lbl_genre;
    private String movie_id = "",video="",poster_path="",backdrop_path="", overview="", release_date="",
            title="", leanguage="", popularity= "",vote_count="",vote_average="", original_title="";
    private MovieViewModel view_model;
    private LinearLayout layout, layout_cast;
    private RecyclerView rv_cast;
    private ProgressBar pro_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        lbl_movie_id = view.findViewById(R.id.lbl_movie_details);
        lbl_text = view.findViewById(R.id.lbl_movie_details);
        posterpath = view.findViewById(R.id.posterpath_movie_details_fragment);
        backdroppath = view.findViewById(R.id.backdrop_path_movie_details_fragment);
        layout = view.findViewById(R.id.li_image_com_fragment);
        lbl_title = view.findViewById(R.id.lbl_title_movie_details_fragment);
        lbl_releasedate = view.findViewById(R.id.releasedate_movie_details_fragment);
        lbl_language = view.findViewById(R.id.lbl_language_movie_details_fragment);
        lbl_popularity = view.findViewById(R.id.lbl_popularity_movie_details_fragment);
        lbl_votecount = view.findViewById(R.id.lbl_votecount_movie_details);
        lbl_voteaverage = view.findViewById(R.id.lbl_voteaverage_movie_details_fragment);
        lbl_overview = view.findViewById(R.id.lbl_overview_movie_details_fragment);
        lbl_runtime = view.findViewById(R.id.lbl_runtime_movie_details);
        lbl_genre = view.findViewById(R.id.lbl_genre_movie_details_fragment);
        rv_cast = view.findViewById(R.id.rv_movie_details_fragment);
        pro_bar = view.findViewById(R.id.progressBar_movie_details);

        movie_id = getArguments().getString("movie_id");
        poster_path = getArguments().getString("movie_poster");
        backdrop_path = getArguments().getString("movie_backdrop");
        title = getArguments().getString("movie_title");
        release_date = getArguments().getString("movie_releasedate");
        overview = getArguments().getString("movie_overview");
        leanguage = getArguments().getString("movie_leanguage");
        popularity = getArguments().getString("movie_popularity");
        vote_count = getArguments().getString("movie_vote");
        vote_average = getArguments().getString("movie_average");
        String movieId = getArguments().getString("movieId");

        String img_path = Const.IMG_URL + poster_path;
        Glide.with(MovieDetailsFragment.this).load(img_path).into(posterpath);

        String img_backdrop = Const.IMG_URL + backdrop_path;
        Glide.with(MovieDetailsFragment.this).load(img_backdrop).into(backdroppath);

        lbl_title.setText(title);
        lbl_releasedate.setText(release_date);
        lbl_overview.setText(overview);
        lbl_language.setText(leanguage);
        lbl_popularity.setText(popularity);
        lbl_votecount.setText(vote_count);
        lbl_voteaverage.setText(vote_average);

        pro_bar.setVisibility(View.VISIBLE);

        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model.getMovieById(movie_id);
        view_model.getResultGetMovieById().observe(getActivity(), showResultMovies);
        view_model.getMoviesCast(movie_id);
        view_model.getResultMoviesCast().observe(getActivity(), showResultsCredits);
        return view;

    }
    private Observer<MoviesCast> showResultsCredits = new Observer<MoviesCast>() {
        @Override
        public void onChanged(MoviesCast moviesCast) {
            pro_bar.setVisibility(View.GONE);
            rv_cast.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
            MoviesCastAdapter adapter = new MoviesCastAdapter(getActivity());
            adapter.setListCredits(moviesCast.getCast());
            rv_cast.setAdapter(adapter);
        }
    };


    private Observer<Movies> showResultMovies = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String genre="";
            String runtime="";
            for (int i = 0; i<movies.getGenres().size(); i++){
                genre += movies.getGenres().get(i).getName();
                if(i == movies.getGenres().size()-1){

                }else{
                    genre +=", ";
                }
            }
            lbl_runtime.setText(String.valueOf(movies.getRuntime()));
            lbl_genre.setText(genre);

            for (int i = 0; i < movies.getProduction_companies().size(); i++){
                ImageView imageView = new ImageView(layout.getContext());
                String comlogo = Const.IMG_URL + movies.getProduction_companies().get(i).getLogo_path();
                String comname = movies.getProduction_companies().get(i).getName();
                if (movies.getProduction_companies().get(i).getLogo_path() == null){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_movie_24,getActivity().getTheme()));
                }else if(comlogo !="https://image.tmdb.org/3/t/p/w500/null"){
                    Glide.with(getActivity()).load(comlogo).into(imageView);
                }
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(250, 250);
                lp.setMargins(20, 0, 20, 0);
                imageView.setLayoutParams(lp);
                layout.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar = Snackbar.make(view, comname, Snackbar.LENGTH_SHORT);
                        snackbar.setAnchorView(R.id.bottom_nav_main_menu);
                        snackbar.show();
                    }
                });
            }
        }
    };
}