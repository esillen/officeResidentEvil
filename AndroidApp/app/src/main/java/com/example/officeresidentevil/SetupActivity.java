package com.example.officeresidentevil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {

    public static final String IP_EXTRA = "IP_EXTRA";
    public static final String PORT_EXTRA = "PORT_EXTRA";


    private EditText directIPText1;
    private EditText directIPText2;
    private EditText directIPText3;
    private EditText directIPText4;
    private EditText directPortText;

    private EditText serviceIPText1;
    private EditText serviceIPText2;
    private EditText serviceIPText3;
    private EditText serviceIPText4;
    private EditText servicePortText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        directIPText1 = (EditText)findViewById(R.id.direct_ip_text_1);
        directIPText2 = (EditText)findViewById(R.id.direct_ip_text_2);
        directIPText3 = (EditText)findViewById(R.id.direct_ip_text_3);
        directIPText4 = (EditText)findViewById(R.id.direct_ip_text_4);
        directPortText = (EditText)findViewById(R.id.direct_port_text);

        serviceIPText1 = (EditText)findViewById(R.id.service_ip_text_1);
        serviceIPText2 = (EditText)findViewById(R.id.service_ip_text_2);
        serviceIPText3 = (EditText)findViewById(R.id.service_ip_text_3);
        serviceIPText4 = (EditText)findViewById(R.id.service_ip_text_4);
        servicePortText = (EditText)findViewById(R.id.service_port_text);


        Button directConnectionButton = (Button)findViewById(R.id.direct_connection_button);
        directConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDirectConnection();
            }
        });

        Button cameraServiceButton = (Button)findViewById(R.id.camera_service_button);
        cameraServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectToService();
            }
        });
    }

    private void startDirectConnection() {

        String directIp = directIPText1.getText().toString() + "."
                        + directIPText2.getText().toString() + "."
                        + directIPText3.getText().toString() + "."
                        + directIPText4.getText().toString();
        int directPort = Integer.parseInt(directPortText.getText().toString());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(IP_EXTRA, directIp);
        intent.putExtra(PORT_EXTRA, directPort);

        startActivity(intent);

    }

    private void connectToService() {

        String serviceIp = serviceIPText1.getText().toString() + "."
                        + serviceIPText2.getText().toString() + "."
                        + serviceIPText3.getText().toString() + "."
                        + serviceIPText4.getText().toString();
        int servicePort = Integer.parseInt(servicePortText.getText().toString());

        Intent intent = new Intent(this, CameraServiceActivity.class);
        intent.putExtra(IP_EXTRA, serviceIp);
        intent.putExtra(PORT_EXTRA, servicePort);

        startActivity(intent);


    }

}
