package com.example.moviedb.view.fragments;

import android.app.Service;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviedb.BuildConfig;
import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.helper.Const;
import com.example.moviedb.helper.EndlessRecyclerViewScrollListener;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.retrofit.ApiEndPoint;
import com.example.moviedb.retrofit.ApiService;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
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

    private RecyclerView rv_now_playing;
    private MovieViewModel view_model;
    private ProgressBar pro_bar;
    NowPlayingAdapter adapter;
    private LinearLayoutManager manager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        view_model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        rv_now_playing = view.findViewById(R.id.rv_now_playing_fragment);
        pro_bar = view.findViewById(R.id.progressBar_now_playing_fragment);
        pro_bar.setVisibility(View.VISIBLE);
        rv_now_playing.setVisibility(View.GONE);
        view_model.getNowPlaying();
        view_model.getResultNowPlaying().observe(getActivity(), showNowPlaying);
        manager = new LinearLayoutManager(getActivity());
        adapter = new NowPlayingAdapter(getActivity());
        return view;
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            pro_bar.setVisibility(View.GONE);
            rv_now_playing.setVisibility(View.VISIBLE);
            rv_now_playing.setLayoutManager(manager);
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);









//            ItemClickSupport.addTo(rv_now_playing).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
//                    return false;
//                }
//            });

//            ItemClickSupport.addTo(rv_now_playing).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//                @Override
//                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("movieId", ""+ nowPlaying.getResults().get(position).getId());
//                    Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_MovieDetailsFragment, bundle);
//                }
//            });
        }
    };
}