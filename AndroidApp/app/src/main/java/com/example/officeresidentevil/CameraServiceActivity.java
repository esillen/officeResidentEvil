package com.example.officeresidentevil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yjkim.mjpegviewer.MjpegView;

public class CameraServiceActivity extends AppCompatActivity {

    private MjpegView mjpegViewerLeft;
    private MjpegView mjpegViewerRight;
    private TextView textView;
    private Socket socket;

    private boolean streamsStarted = false;

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(() -> {
                String text = (String)args[0];
                textView.setText(text);
                tryStartVideoStream(text);
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_service);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mjpegViewerLeft = findViewById(R.id.mjpegview_left);
        mjpegViewerRight = findViewById(R.id.mjpegview_right);

        textView = findViewById(R.id.textView);

        Bundle extras = getIntent().getExtras();
        String cameraServiceIP = extras.getString(SetupActivity.IP_EXTRA);
        int cameraServicePort = extras.getInt(SetupActivity.PORT_EXTRA);

        try {
            socket = IO.socket("http://" + cameraServiceIP + ":" + cameraServicePort);

            socket.on("new message", onNewMessage);

            socket.connect();

        } catch (URISyntaxException e) {
            textView.setText(e.toString());
        }

    }

   /* private void setVideoUrl(String url) {
        if (!streamsStarted) {

            mjpegViewerLeft.setMode(MjpegView.MODE_FIT_WIDTH);
            mjpegViewerLeft.setAdjustHeight(true);

            mjpegViewerRight.setMode(MjpegView.MODE_FIT_WIDTH);
            mjpegViewerRight.setAdjustHeight(true);

        } else {

            mjpegViewerLeft.stopStream();
            mjpegViewerRight.stopStream();

        }

        mjpegViewerLeft.setUrl(url);
        mjpegViewerLeft.startStream();

        mjpegViewerRight.setUrl(url);
        mjpegViewerRight.startStream();

        streamsStarted = true;
    }
*/
   /* private void setVideoUrl2(String url) {
        int TIMEOUT = 5; //seconds

         Mjpeg leftInstance = Mjpeg.newInstance();
         leftInstance.open(url,  TIMEOUT)
                .subscribe(inputStream -> {
                    mjpegViewerLeft.setSource(inputStream);
                    mjpegViewerLeft.setDisplayMode(DisplayMode.FULLSCREEN);
                    mjpegViewerLeft.showFps(true);
                });



        Mjpeg.newInstance()
                .open(url,  TIMEOUT)
                .subscribe(inputStream -> {
                    mjpegViewerRight.setSource(inputStream);
                    mjpegViewerRight.setDisplayMode(DisplayMode.FULLSCREEN);
                    mjpegViewerRight.showFps(true);
                });
    }*/

   private void setVideoUrl3(String url) {
        mjpegViewerLeft.Start(url);
        mjpegViewerRight.Start(url);
   }

    // TODO: better code, error handling etc etc...
    private void tryStartVideoStream (String msg) {

        String pattern = "(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(msg);
        if (matcher.find()) {
            String ip = matcher.group(1);
            String port = matcher.group(2);
            try {
                int portnumber = Integer.parseInt(port);
                String url = "http://" + ip + ":" + portnumber;
                Log.d("VIDEO_VIEW", url);
                setVideoUrl3(url);
            } catch (Exception e) {
                Log.w("VIDEO_VIEW", "probably failed parsing the int" + e.toString());
            }
        } else {
            Log.e("VIDEO_VIEW", "probably could not match");
        }

    }

}
