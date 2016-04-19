package jszum.acceleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Networking extends AppCompatActivity {

    Button left, right;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        left    = (Button) findViewById(R.id.buttonL);
        right   = (Button) findViewById(R.id.buttonR);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication().getBaseContext(),"Left clicked", Toast.LENGTH_LONG);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication().getBaseContext(),"Right clicked", Toast.LENGTH_LONG);
            }
        });

    }
}
