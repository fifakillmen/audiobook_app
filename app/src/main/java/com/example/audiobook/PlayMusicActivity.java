package com.example.audiobook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayMusicActivity extends AppCompatActivity {
    private ImageView imagePlayPause;
    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekbar, volumeSeekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volumeLevel = (float) currentVolume / maxVolume;
        imagePlayPause = findViewById(R.id.btnPlay);
        textCurrentTime = findViewById(R.id.txtCurrentTime);
        textTotalDuration = findViewById(R.id.txtTotalDuration);
        playerSeekbar = findViewById(R.id.seekBar);
        mediaPlayer = new MediaPlayer();
        volumeSeekbar = findViewById(R.id.seekBar2);
        playerSeekbar.setMax(100);

        volumeSeekbar.setProgress((int) (volumeLevel * 100));
        imagePlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imagePlayPause.setImageResource(android.R.drawable.ic_media_play);
                } else {
                    mediaPlayer.start();
                    imagePlayPause.setImageResource(android.R.drawable.ic_media_pause);
                    updateSeekbar();
                }
            }
        });

        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Set the audio volume based on the progress
                float volume = progress / 100.0f; // Progress should be between 0 and 100
                mediaPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed for volume control
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed for volume control
            }
        });

        prepareMediaPlayer();
    }

    private void prepareMediaPlayer() {
        try {
            mediaPlayer.setDataSource("http://commondatastorage.googleapis.com/codeskulptor-demos/DDR_assets/Kangaroo_MusiQue_-_The_Neverwritten_Role_Playing_Game.mp3");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    textTotalDuration.setText(millisecondsToTimer(mp.getDuration()));
                    mp.start();
                    updateSeekbar();
                }
            });
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            long currentDuration = mediaPlayer.getCurrentPosition();
            textCurrentTime.setText(millisecondsToTimer(currentDuration));
            updateSeekbar();
            handler.postDelayed(updater, 1000); // Update seekbar and time every 1 second
        }
    };

    private void updateSeekbar() {
        if (mediaPlayer.isPlaying()) {
            playerSeekbar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration() * 100)));
            handler.postDelayed(updater, 1000); // Schedule the updater to run again in 1 second
        }
    }

    private String millisecondsToTimer(long milliseconds) {
        String timerString = "";
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) (milliseconds % (1000 * 60)) / 1000;

        if (hours > 0) {
            timerString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            timerString = String.format("%02d:%02d", minutes, seconds);
        }

        return timerString;
    }
}