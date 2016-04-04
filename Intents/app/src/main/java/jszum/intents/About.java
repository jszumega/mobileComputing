package jszum.intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button buttonOk = (Button) findViewById(R.id.buttonOK);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent myCaller = getIntent();
        Bundle mybundle = new Bundle();

        mybundle.putSerializable("about", "Author: Jarosław Szumega");
        myCaller.putExtras(mybundle);


        setResult(Activity.RESULT_OK, myCaller);
    }
}
