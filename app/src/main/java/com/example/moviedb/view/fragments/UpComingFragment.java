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
import android.widget.ProgressBar;

import com.example.moviedb.R;
import com.example.moviedb.adapter.UpComingAdapter;
import com.example.moviedb.model.UpComing;
import com.example.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpComingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpComingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpComingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment upComingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpComingFragment newInstance(String param1, String param2) {
        UpComingFragment fragment = new UpComingFragment();
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

    private RecyclerView rv_up_coming;
    private MovieViewModel viewModel;
    private ProgressBar pro_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_up_coming,container,false);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        rv_up_coming = view.findViewById(R.id.rv_up_coming_fragment);
        pro_bar = view.findViewById(R.id.progressBar_up_coming_fragment);
        pro_bar.setVisibility(View.VISIBLE);
        rv_up_coming.setVisibility(View.GONE);
        viewModel.getUpComing();
        viewModel.getResultUpComing().observe(getActivity(), showUpComing);
        return view;
    }

    private Observer<UpComing> showUpComing = new Observer<UpComing>() {
        @Override
        public void onChanged(UpComing upComing) {
            rv_up_coming.setLayoutManager(new LinearLayoutManager(getActivity()));
            pro_bar.setVisibility(View.GONE);
            rv_up_coming.setVisibility(View.VISIBLE);
            UpComingAdapter adapter = new UpComingAdapter(getActivity());
            adapter.setListUpComing(upComing.getResults());
            rv_up_coming.setAdapter(adapter);
        }
    };
}