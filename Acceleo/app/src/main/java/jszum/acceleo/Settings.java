package jszum.acceleo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button buttonOk = (Button) findViewById(R.id.buttonAccept);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myCaller = getIntent();
                Bundle mybundle = new Bundle();

                EditText textIP = (EditText) findViewById(R.id.editIP);
                EditText textPort = (EditText) findViewById(R.id.editPort);

                String ipAddress =  textIP.getText().toString();
                String portNumber = textPort.getText().toString();

                mybundle.putSerializable("ip", ipAddress);
                mybundle.putSerializable("port", portNumber);
                myCaller.putExtras(mybundle);

                setResult(Activity.RESULT_OK, myCaller);

                finish();
            }
        });
    }
}
