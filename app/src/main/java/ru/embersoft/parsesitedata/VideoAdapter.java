package ru.embersoft.parsesitedata;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    private ArrayList<VideoItem> videoItems;
    private Context context;

    public VideoAdapter(ArrayList<VideoItem> videoItems, Context context) {
        this.videoItems = videoItems;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoAdapter.ViewHolder holder, int position) {
        VideoItem videoItem = videoItems.get(position);

        Picasso.get().load(videoItem.getPreviewUrl()).into(holder.previewImaegView);

        try {
            String link = videoItem.getVideoUrl();
            MediaController mediaController = new MediaController(context);
            mediaController.setAnchorView(holder.videoView);
            Uri video = Uri.parse(link);
            holder.videoView.setMediaController(mediaController);
            holder.videoView.setVideoURI(video);
            holder.previewImaegView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.previewImaegView.setVisibility(View.GONE);
                    holder.videoView.start();
                }
            });

            holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    holder.previewImaegView.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "Error connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        VideoView videoView;
        ImageView previewImaegView;

        public ViewHolder(@NonNull View view) {
            super(view);
            videoView = view.findViewById(R.id.videoView);
            previewImaegView = view.findViewById(R.id.previewImageView);
        }

    }
}
