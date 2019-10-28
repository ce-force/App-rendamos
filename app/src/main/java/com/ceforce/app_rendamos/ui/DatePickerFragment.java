package com.ceforce.app_rendamos.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    static int day;
    static int month;
    static int year;

    public static void setTv(TextView tv) {
        DatePickerFragment.tv = tv;
    }

    public static TextView tv;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        this.day=day;
        this.month=month;
        this.year=year;
        setTextView();
        /*Log.d("STATUS","day"+day);
        Log.d("STATUS","month"+month);
        Log.d("STATUS","year"+year);*/
    }


    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public String getDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date parse = null;
        try {
            parse = sdf.parse(""+this.getDay()+"/"+(this.getMonth()+1)+"/"+this.getYear()+"/");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(parse);
    }

    public void setTextView(){
        tv.setText(getDateString());
    }
}
