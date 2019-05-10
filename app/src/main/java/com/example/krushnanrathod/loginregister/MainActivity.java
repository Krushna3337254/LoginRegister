package com.example.krushnanrathod.loginregister;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    EditText e1,e2,e3,e4;



    DatePickerDialog datePickerDialog;
    RadioGroup radioGroup;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        t2=findViewById( R.id.textView2 );
        e4=findViewById( R.id.editText4 );

        radioGroup=findViewById( R.id.radiogrp );
        final Spinner spinner = (Spinner) findViewById(R.id.country);


        db = openOrCreateDatabase( "StudentDB", Context.MODE_PRIVATE, null );                    //opening or creating databasefrom invoking object
        db.execSQL( "CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);" );       //creating table by firing sql Query


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Spinner Code

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Toast.makeText(getApplicationContext(), "Selected : "+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                t2.setText(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        } );





//Calendar Code


        e4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c=Calendar.getInstance();
                int myear=c.get(Calendar.YEAR);             //current year
                int mMonth=c.get(Calendar.MONTH);           //current Month
                int mday=c.get(Calendar.DAY_OF_MONTH);      ////current Day of month
                //date picker dialog
                datePickerDialog=new DatePickerDialog( MainActivity.this, new DatePickerDialog.OnDateSetListener(  ) {


                    @Override
                    public void onDateSet(DatePicker view, int year, int monthofyear, int dateofMonth) {

                        e4.setText( dateofMonth + "/" + (monthofyear+1) + "/" + year );
                    }
                },myear,mMonth,mday);
                datePickerDialog.show();
            }
        } );




//Radio Button

        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.R1:
                        Toast.makeText( MainActivity.this, "Selected Male", Toast.LENGTH_SHORT ).show();
                        break;

                    case R.id.R2:
                        Toast.makeText( MainActivity.this, "Selected Female", Toast.LENGTH_SHORT ).show();
                        break;
                }
            }
        } );



    }


}