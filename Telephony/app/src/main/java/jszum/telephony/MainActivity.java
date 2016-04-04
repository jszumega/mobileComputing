package jszum.telephony;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static OutgoingCallReceiver caller = new OutgoingCallReceiver();

    TelephonyManager telephonyManager;
    TextView informations;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 999;

    PhoneStateListener stateListener = new PhoneStateListener();
    signalStrengthListener signalListener = new signalStrengthListener();
    int SignalStrength = 1;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String srvcName = Context.TELEPHONY_SERVICE;
        telephonyManager = (TelephonyManager)getSystemService(srvcName);
        informations = (TextView) findViewById(R.id.textView);

        Button gather = (Button) findViewById(R.id.buttonGather);
        bar = (ProgressBar) findViewById(R.id.progressBar);

        gather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherInformation();
            }
        });

        telephonyManager.listen(signalListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        String fullInfo = "Phone Type: " + "\n"
                + "Device ID: "  + "\n"
                + "Data State "  + "\n";
        informations.setText(fullInfo);
        bar.setProgress(SignalStrength);
    }

    private void gatherInformation() {
        int phoneType = telephonyManager.getPhoneType();
        String phone = decidePhoneType(phoneType);

        String deviceId = telephonyManager.getDeviceId();

        int dataState = telephonyManager.getDataState();
        String data = getDataState(dataState);

        String fullInfo = "Phone Type: "+ phone + "\n"
                        + "Device ID: " + deviceId + "\n"
                        + "Data State " + data + "\n";
        informations.setText(fullInfo);

    }

    private String decidePhoneType(int phoneType) {
        String phoneTypeString = new String();

        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                phoneTypeString = "CDMA";
                break ;
            case (TelephonyManager.PHONE_TYPE_GSM) :
                phoneTypeString = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                phoneTypeString = "NONE";
                break;
            default:
                phoneTypeString = "UNKNOWN TYPE";
                break;
        }
        return phoneTypeString;
    }

    private String getDataState(int dataState) {
        String dataStateString = new String();
        switch (dataState) {
            case TelephonyManager.DATA_CONNECTED: //Connected.
                dataStateString = "CONNECTED";
                break;
            case TelephonyManager.DATA_CONNECTING: //Currently setting up data connection
                dataStateString = "CONNECTING";
                break;
            case TelephonyManager.DATA_DISCONNECTED: //Disconnected
                dataStateString = "DISCONNECTED";
                break;
            case TelephonyManager.DATA_SUSPENDED: //Suspended
                dataStateString = "SUSPENDED";
                break;
        }

        return dataStateString;
    }



    public class signalStrengthListener extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            //SignalStrength = signalStrength.getGsmSgnalStrength();
            SignalStrength = signalStrength.getLevel(); // level 0-4
            bar.setProgress(SignalStrength);
        }
    }
}

