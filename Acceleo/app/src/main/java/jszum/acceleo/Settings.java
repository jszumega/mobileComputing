package jszum.acceleo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button buttonOk = (Button) findViewById(R.id.buttonAccept);
        TextView textIP = (TextView) findViewById(R.id.textIP);
        TextView textPort = (TextView) findViewById(R.id.textPort);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent myCaller = getIntent();
        Bundle mybundle = new Bundle();

        mybundle.putSerializable("ip", textIP.getText().toString());
        mybundle.putSerializable("port", textPort.getText().toString());
        myCaller.putExtras(mybundle);

        setResult(Activity.RESULT_OK, myCaller);
    }
}
