package com.example.officeresidentevil;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegSurfaceView;
import com.longdo.mjpegviewer.MjpegView;

public class MainActivity extends AppCompatActivity {

    private String streamIp = "";
    private int streamPort;
    private MjpegView mjpegViewerLeft;
    private MjpegView mjpegViewerRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();


        mjpegViewerLeft = findViewById(R.id.mjpegview_left);
        mjpegViewerRight = findViewById(R.id.mjpegview_right);


        Bundle extras = getIntent().getExtras();
        streamIp = extras.getString(SetupActivity.IP_EXTRA);
        streamPort = extras.getInt(SetupActivity.PORT_EXTRA);

        String url = "http://" + streamIp + ":" + streamPort;
        Log.d("VIDEO_VIEW", url);
        setVideoUri(url);
    }

    private void setVideoUri(String url) {

        mjpegViewerLeft.setMode(MjpegView.MODE_FIT_WIDTH);
        mjpegViewerLeft.setAdjustHeight(true);
        mjpegViewerLeft.setUrl(url);
        mjpegViewerLeft.startStream();

        mjpegViewerRight.setMode(MjpegView.MODE_FIT_WIDTH);
        mjpegViewerRight.setAdjustHeight(true);
        mjpegViewerRight.setUrl(url);
        mjpegViewerRight.startStream();


        /*int TIMEOUT = 5;

        Mjpeg.newInstance()
                .credential("", "")
                .open(uri.toString(), TIMEOUT)
                .subscribe(inputStream -> {
                    mjpegViewerLeft.setSource(inputStream);
                    mjpegViewerLeft.setDisplayMode(DisplayMode.BEST_FIT);
                    // mjpegViewerRight.setSource(inputStream);
                    // mjpegViewerRight.setDisplayMode(DisplayMode.BEST_FIT);
                });
        */
    }


}
