package in.co.gstsamadhan.gstsamadhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class VideoSection extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    Intent i;
    public static final String API_KEY = "AIzaSyCv8mOgiCNpk7yYT4QEcIiKfJyqNc2BPkc";
    String VIDEO_ID;
    char h[];
    String videourl,videotitle,videodate;
    TextView videoDate,videoTitle,videoDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_section);
        videoDate = findViewById(R.id.videoDateSection);
        videoTitle = findViewById(R.id.videoTitleSection);
        videoDesc = findViewById(R.id.videoDescriptionSection);
        i=getIntent();
        videourl=i.getStringExtra("video_url");

        videoDate.setText(i.getStringExtra("dated"));
        videoTitle.setText(i.getStringExtra("title"));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            videoDesc.setText(Html.fromHtml(i.getStringExtra("description"),Html.FROM_HTML_MODE_LEGACY));
        } else {
            videoDesc.setText(Html.fromHtml(i.getStringExtra("description")));
        }


        /** Initializing YouTube Player View **/
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
        VIDEO_ID = videourl.replace("https://www.youtube.com/embed/","");

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
        }
        @Override
        public void onVideoStarted() {
        }
    };

    @Override
    public void onBackPressed() {

        finish();
        overridePendingTransition(0,0);
    }
}
