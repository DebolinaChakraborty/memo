package com.example.dell.myapplication.AddMemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dell.myapplication.Activitybo;
import com.example.dell.myapplication.Dbhandler.Dbhandler;
import com.example.dell.myapplication.Memo;
import com.example.dell.myapplication.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMemo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddMemo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMemo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int hour;
    private int minute;
    private OnFragmentInteractionListener mListener;

    public AddMemo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMemo.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMemo newInstance(String param1, String param2) {
        AddMemo fragment = new AddMemo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ImageButton cal = (ImageButton)getActivity().findViewById(R.id.dpcal);
        final EditText tp =(EditText) getActivity().findViewById(R.id.input_Time);
        final Button User =(Button) getActivity().findViewById(R.id.btn_Add);
        final EditText des = (EditText) getActivity().findViewById(R.id.input_Desc);
        final EditText loc = (EditText) getActivity().findViewById(R.id.input_Location);
        final Button add = (Button)getActivity().findViewById(R.id.btn_Add) ;
        final EditText editText = (EditText) getActivity().findViewById(R.id.input_Date);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerCalender();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dbhandler dbhandler = new Dbhandler();
                Activitybo activitybo = new Activitybo();
                activitybo.setDescription(des.getText().toString());
                activitybo.setLocation(loc.getText().toString());
                activitybo.setDate(editText.getText().toString());
                activitybo.setTime(tp.getText().toString());
                activitybo.setUname(dbhandler.getisOnline());

                dbhandler.insertActivityDetails(activitybo);
                Toast.makeText(Memo.getContext(), "Memo Successfully Added", Toast.LENGTH_LONG).show();
            }
        });

    }
    public  void datePickerCalender()
    {
        DialogFragment picker = new Addmemodate();
        picker.show(getFragmentManager(), "datePicker");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_memo, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}