package jszum.intents;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT = 1;
    static final int REQ_ABOUT = 999;

    private ArrayList<Button> buttons;
    String number ="";
    private TextView phone;
    private TextView latitude;
    private TextView longtitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = (TextView) findViewById(R.id.telephon);
        latitude = (TextView) findViewById(R.id.latText);
        longtitude = (TextView) findViewById(R.id.longText);

        //Departments of electronics coordinates
        latitude.setText("51.1090539");
        longtitude.setText("17.0596592");


        buttons = new ArrayList<Button>();
        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.buttonSMS));
        buttons.add((Button) findViewById(R.id.buttonMap));
        buttons.add((Button) findViewById(R.id.buttonPlace));
        buttons.add((Button) findViewById(R.id.buttonAbout));

        buttons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("content://contacts");

                Intent contactActivity = new Intent(Intent.ACTION_PICK, uri);
                contactActivity.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(contactActivity, PICK_CONTACT);
            }
        });

        buttons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse("smsto:"+number));
                intent.putExtra("sms_body",
                        "Hello, pleased to meet you!");
                startActivity(intent);

            }
        });

        buttons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String latitudeValue = latitude.getText().toString();
                String longtitudeValue = longtitude.getText().toString();

                Intent intent=new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:"+latitudeValue+","+longtitudeValue));
                startActivity(intent);

            }
        });

        buttons.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thePlace="Wydział Elektroniki Politechnika Wrocławska, Wrocław";
                Intent intent=new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=("+thePlace+")"));
                startActivity(intent);

            }
        });

        buttons.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thePlace="Wydział Elektroniki Politechnika Wrocławska, Wrocław";
                Intent intent=new Intent(MainActivity.this, About.class);
                startActivityForResult(intent,REQ_ABOUT);

            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);

        switch(reqCode) {
            case PICK_CONTACT:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER,
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };

                    Cursor cursor = getContentResolver().query(uri, projection,
                            null, null, null);
                    cursor.moveToFirst();

                    int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String phNumber = cursor.getString(numberColumnIndex);
                    int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String name = cursor.getString(nameColumnIndex);

                    phone.setText(phNumber);
                    number = phNumber;
                }
                break;
            case REQ_ABOUT:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle returned = data.getExtras();
                    String author = returned.getString("about");

                    TextView tv = (TextView) findViewById(R.id.authorText);
                    tv.setText((CharSequence)author);
                }
                break;
        }
    }
}
