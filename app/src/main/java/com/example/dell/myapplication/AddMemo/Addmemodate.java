package com.example.dell.myapplication.AddMemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dell.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by DELL on 5/21/2016.
 */
public class Addmemodate extends android.support.v4.app.DialogFragment
        implements DatePickerDialog.OnDateSetListener  {



    private String date;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        long ss =c.get(Calendar.MILLISECOND);

        date =day+"-"+month+"-"+year+"";
        DatePickerDialog dp = new DatePickerDialog(getActivity(), this, year, month, day);

        dp.getDatePicker().setCalendarViewShown(false);


//        Calendar minDate = Calendar.getInstance();
//        Calendar maxDate = Calendar.getInstance();
//
//        minDate.set(Calendar. );
//        maxDate.set(Calendar.DAY_OF_MONTH, maxDate.get(Calendar.DAY_OF_MONTH) + (Order.checkOrderCutOffTimeForNormalDaySpan()));
//
//        dp.getDatePicker().setMinDate(minDate.getTimeInMillis() - 100000);
//        dp.getDatePicker().setMaxDate(maxDate.getTimeInMillis());


        return dp;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
//        if(Utils.checkOrderCutOffTime())
//        {
//            EditText editText = (EditText) getActivity().findViewById(R.id.datePickerEditText);
//            String abc = "";
//            editText.setText(abc);
//            Button dateImage = (Button) getActivity().findViewById(R.id.datePickerImageButton);
//            dateImage.setText(abc);
//        }

        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        date = sdf.format(c.getTime());
        EditText editText = (EditText) getActivity().findViewById(R.id.input_Date);
        editText.setText(date);
        SimpleDateFormat sdfForImage = new SimpleDateFormat("dd-MMM");
        date = sdfForImage.format(c.getTime());
//            Button dateImage = (Button) getActivity().findViewById(R.id.datePickerImageButton);
//            dateImage.setText(date.split("-")[0] + "\n" + date.split("-")[1]);
//            dateImage.setGravity(Gravity.CENTER | Gravity.BOTTOM);

//
//        dateImage.setText(Html.fromHtml("<b>" + s1 + "</b>" + "<br />" +
//                "<small>" + s2 + "</small>" + "<br />"));
//        dateImage.setGravity(10);
    }
}
