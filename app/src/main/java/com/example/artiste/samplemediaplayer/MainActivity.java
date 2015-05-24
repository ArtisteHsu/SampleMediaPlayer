package com.example.artiste.samplemediaplayer;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private MediaPlayer sampleMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleMediaPlayer = new MediaPlayer();
        sampleMediaPlayer.setOnCompletionListener(this);
        sampleMediaPlayer.setOnPreparedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_audio) {
            AssetFileDescriptor afd;
            afd = getBaseContext().getResources().openRawResourceFd(R.raw.handel_water_music);
            try {
                sampleMediaPlayer.reset();
                sampleMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
                sampleMediaPlayer.prepare();
                sampleMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (id == R.id.action_video) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Implementation of MediaPlayer Listener callbacks
    @Override
    public void onCompletion(MediaPlayer mp) {
        TextView t = (TextView)findViewById(R.id.textview_time);
        t.setText("Complete");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        TextView t = (TextView)findViewById(R.id.textview_time);
        MediaPlayer.TrackInfo info[] = mp.getTrackInfo();
        t.setText(info[0].toString());
    }
}
