package com.annhienktuit.cleanarchitectureplayer.ui.songlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.annhienktuit.cleanarchitectureplayer.R;
import com.annhienktuit.domain.models.Song;

import java.util.List;


public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private List<Song> songList;

    private AllSongPresenter presenter;

    public SongListAdapter(AllSongPresenter presenter){
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListAdapter.ViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.titleTextView.setText(song.getTitle());
        holder.itemView.setOnClickListener(v -> presenter.onSongItemClick(song));
    }

    @Override
    public int getItemCount() {
        if(songList == null) return 0;
        return songList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;

        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvSongTitle);
        }
    }

    public void setData(List<Song> songList){
        this.songList = songList;
    }
}
