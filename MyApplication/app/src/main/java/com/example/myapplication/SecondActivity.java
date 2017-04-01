package com.example.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

//import android.support.annotation.RequiresApi;

/**
 * Created by 小妖王 on 2017/2/10.
 */

public class SecondActivity extends Activity {
    private Button button;
    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;
    private TextView textView;
    private Button dateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button= (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("fuck",SecondActivity.this.button.getText());
                setResult(1,data);
                finish();
            }
        });

        //calendar=Calendar.getInstance();
        textView=(TextView)findViewById(R.id.textView3);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        year= datePicker.getYear();
        month = datePicker.getMonth()+1;
        day = datePicker.getDayOfMonth();
        textView.setText(year + "-" + month + "-" + day);
        datePicker.init(year, datePicker.getMonth(), day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year + "-" + (monthOfYear +1)+ "-" + dayOfMonth);
            }
        });

        dateButton = (Button)findViewById(R.id.dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(SecondActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textView.setText(year + "-" + (month +1)+ "-" + dayOfMonth);
                    }
                },year, datePicker.getMonth(), day);
                datePickerDialog.show();
            }
        });

    }

}

