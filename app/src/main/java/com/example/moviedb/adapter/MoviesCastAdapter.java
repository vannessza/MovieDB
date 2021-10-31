package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.MoviesCast;

import java.util.List;

public class MoviesCastAdapter extends RecyclerView.Adapter<MoviesCastAdapter.MoviesCastHolder> {
    private Context context;
    private List<MoviesCast.Cast> listCredits;
    private List<MoviesCast.Cast> getListCredits(){
        return listCredits;
    }

    public void setListCredits(List<MoviesCast.Cast>listCredits){
        this.listCredits = listCredits;
    }
    public MoviesCastAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public MoviesCastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cast,parent,false);
        return new MoviesCastAdapter.MoviesCastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesCastAdapter.MoviesCastHolder holder, int position) {
        final MoviesCast.Cast cast = getListCredits().get(position);
        holder.lbl_cast.setText(cast.getName());
        holder.lbl_character.setText(cast.getCharacter());
        Glide.with(context)
                .load(Const.IMG_URL + cast.getProfile_path())
                .into(holder.img_cast);

    }

    @Override
    public int getItemCount() {
        return getListCredits().size();
    }

    public class MoviesCastHolder extends RecyclerView.ViewHolder {
        ImageView img_cast;
        TextView lbl_cast, lbl_character;

        public MoviesCastHolder(@NonNull View itemView) {
            super(itemView);
            img_cast = itemView.findViewById(R.id.img_card_cast);
            lbl_cast = itemView.findViewById(R.id.lbl_card_cast);
            lbl_character = itemView.findViewById(R.id.lbl_caracter_card_cast);
        }
    }
}
