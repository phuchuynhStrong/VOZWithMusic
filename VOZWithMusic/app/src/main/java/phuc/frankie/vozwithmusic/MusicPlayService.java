package phuc.frankie.vozwithmusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frank on 13/08/2017.
 */

public class MusicPlayService extends Service {

    ArrayList<Integer> listSongs;
    int isPlayingNumber = 0;
    private static final String TAG = null;
    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();


    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras() == null) return START_STICKY;
        listSongs = intent.getExtras().getIntegerArrayList("songs");
        assert listSongs != null;
        player = MediaPlayer.create(this, listSongs.get(0));
        player.setVolume(100,100);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                    playNext();
            }
        });
        player.start();
        return START_STICKY;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {
        player.stop();
    }
    public void onPause() {
        player.pause();
    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }

    public void playNext() {
        if (listSongs.size()-1 != isPlayingNumber) {
            player = MediaPlayer.create(this, listSongs.get(isPlayingNumber + 1));
            isPlayingNumber++;
            player.setVolume(100, 100);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    playNext();
                }
            });
            player.start();
        }
        else {
            isPlayingNumber = -1;
            playNext();
        }
    }
}
