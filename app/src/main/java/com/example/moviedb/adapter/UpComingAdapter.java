package com.example.moviedb.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.UpComing;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.UpComingHolder> {

    private Context context;
    private List<UpComing.Results> listUpComing;
    private List<UpComing.Results> getListUpComing(){
        return listUpComing;
    }
    public void setListUpComing(List<UpComing.Results>listUpComing){
        this.listUpComing = listUpComing;
    }
    public UpComingAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public UpComingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_up_coming, parent, false);
        return new UpComingAdapter.UpComingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingHolder holder, int position) {
        final UpComing.Results results = getListUpComing().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_release_date.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.img_poster);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("movie_id", ""+ results.getId());
                bundle.putString("movie_poster",""+results.getPoster_path().toString());
                bundle.putString("movie_backdrop",""+results.getBackdrop_path().toString());
                bundle.putString("movie_title", ""+ results.getTitle());
                bundle.putString("movie_releasedate",""+ results.getRelease_date());
                bundle.putString("movie_overview", ""+ results.getOverview());
                bundle.putString("movie_leanguage",""+ results.getOriginal_language());
                bundle.putString("movie_popularity",""+results.getPopularity());
                bundle.putString("movie_vote",""+results.getVote_count());
                bundle.putString("movie_average",""+results.getVote_average());
                bundle.putString("movie_originaltitle",""+results.getOriginal_title());
                bundle.putString("movie_listgenre",""+results.getGenre_ids());
                Navigation.findNavController(view).navigate(R.id.action_upComingFragment_to_movieDetailsFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListUpComing().size();
    }

    public class UpComingHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cv;
        public UpComingHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_title = itemView.findViewById(R.id.lbl_card_upcoming);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card_upcoming);
            lbl_release_date = itemView.findViewById(R.id.lbl_releasedate_card_upcoming);
            cv = itemView.findViewById(R.id.cv_card_upcoming);
        }
    }
}
