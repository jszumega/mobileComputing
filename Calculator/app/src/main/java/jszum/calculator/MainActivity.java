package jszum.calculator;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "Calculator";
    ArrayList<Button> numButtons;
    ArrayList<Button> oprButtons;

    String operation;
    int element1, element2;
    float result;
    boolean input1, input2, operator1, result1, cleared;

    EditText te;
    TextView tv;

    void calculate() {
        result1 = true;
        switch(operation) {
            case "+":
                result = element1 + element2;
                break;
            case "-":
                result = element1 - element2;
                break;
            case "*":
                result = element1 * element2;
                break;
            case "/":
                result = element1 * 1.0f / element2;
                break;
        }
    }

    private void setupNumericButtons() {
        numButtons = new ArrayList<Button>();

        numButtons.add((Button) findViewById(R.id.button0));
        numButtons.add((Button) findViewById(R.id.button1));
        numButtons.add((Button) findViewById(R.id.button2));
        numButtons.add((Button) findViewById(R.id.button3));
        numButtons.add((Button) findViewById(R.id.button4));
        numButtons.add((Button) findViewById(R.id.button5));
        numButtons.add((Button) findViewById(R.id.button6));
        numButtons.add((Button) findViewById(R.id.button7));
        numButtons.add((Button) findViewById(R.id.button8));
        numButtons.add((Button) findViewById(R.id.button9));

        for(Button b : numButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v;

                    if(!cleared) {
                        tv.setText("");
                        cleared = true;
                    }

                    if (input1 == true && operator1 == true) input2 = true;

                    Integer value = Integer.parseInt(tv.getText().toString()+b.getText().toString());

                    tv.setText(value.toString());
                    te.setText(value.toString() +"\n"+ te.getText().toString());
                    input1 = true;

                }
            });
        }
    }

    private void setupOprButtons() {
        oprButtons = new ArrayList<Button>();

        oprButtons.add((Button) findViewById(R.id.buttonplus));
        oprButtons.add((Button) findViewById(R.id.buttonmin));
        oprButtons.add((Button) findViewById(R.id.buttonmul));
        oprButtons.add((Button) findViewById(R.id.buttondiv));
        oprButtons.add((Button) findViewById(R.id.buttoneq));
        oprButtons.add((Button) findViewById(R.id.buttonce));

        for(Button b : oprButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button) v;
                    String operator = "";

                    if(operator1 == false && input1 == true) {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        element1 = Integer.parseInt(tv.getText().toString());
                    }
                    if(operator1 == true && input1 == true && input2 == true) {
                        TextView tv = (TextView) findViewById(R.id.textView);
                        element2 = Integer.parseInt(tv.getText().toString());
                    }
                    tv.setText("");

                        switch (b.getId()) {
                            case R.id.buttonplus:
                                operator = "+";
                                operator1 = true;
                                break;
                            case R.id.buttonmin:
                                operator = "-";
                                operator1 = true;
                                break;
                            case R.id.buttonmul:
                                operator = "*";
                                operator1 = true;
                                break;
                            case R.id.buttondiv:
                                operator = "/";
                                operator1 = true;
                                break;
                            case R.id.buttoneq:
                                input1 = false;
                                input2 = false;
                                operator1 = false;
                                calculate();
                                cleared = false;
                                break;
                            case R.id.buttonce:
                                initValues();
                                operator = "";
                                break;
                        }

                    operation = operator;
                    cleared = false;

                    if(input1 == true && result1 == false) {
                        te.setText(operator + "\n" + te.getText());
                        tv.setText(operator);
                    }

                    if(result1 == true) {
                        tv.setText(Float.toString(result));
                        te.setText("= " + Float.toString(result)+ "\n" + te.getText());
                        result1 = false;
                    }
                }
            });
        }
    }

    void initValues() {
        input1 = false;
        input2 = false;
        operator1 = false;
        result1 = false;
        cleared = true;

        element1 = 0; element2 = 0; result = 0;

        te = (EditText) findViewById(R.id.logText);
        tv = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_port);

        te = (EditText) findViewById(R.id.logText);
        tv = (TextView) findViewById(R.id.textView);

        setupNumericButtons();
        setupOprButtons();
    }
}
