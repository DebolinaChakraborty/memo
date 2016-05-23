//package com.example.dell.myapplication.AddMemo;
//
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.app.TimePickerDialog;
//import android.widget.TimePicker;
//
//import com.example.dell.myapplication.Memo;
//
///**
// * Created by DELL on 5/21/2016.
// */
//public static class Addtime extends android.app.DialogFragment implements TimePickerDialog.OnTimeSetListener {
//
//    private EditText editText;
//
//    public TimePick(EditText editText) {
//        this.editText = editText;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        final Calendar c = Calendar.getInstance();
//        int hour = c.get(Calendar.HOUR_OF_DAY);
//        int minute = c.get(Calendar.MINUTE);
//
//        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
//    }
//
//    @Override
//    public void onTimeSet(TimePicker view, int hourofDay, int minute) {
//        editText.setText(Integer.toString(hourofDay) + ":" + Integer.toString(minute));
//    }
//}