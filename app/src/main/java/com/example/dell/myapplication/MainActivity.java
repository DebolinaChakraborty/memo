package com.example.dell.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dell.myapplication.AddMemo.AddMemo;
import com.example.dell.myapplication.Dbhandler.Dbhandler;
import com.example.dell.myapplication.Login;
import com.example.dell.myapplication.Memo;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.Show.MemoReport;

public class MainActivity extends AppCompatActivity implements AddMemo.OnFragmentInteractionListener,MemoReport.OnFragmentInteractionListener{
    @Override
    public void onResume() {
        super.onResume();
        Memo.setContext(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Memo.setContext(null);
    }

    public static String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Memo.setContext(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        final PlaceholderFragment placeholderFragment = this;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final ImageButton add = (ImageButton) rootView.findViewById(R.id.imgpurchase);
            final ImageButton Users = (ImageButton) rootView.findViewById(R.id.imggrn);

            PlaceholderFragment mHomeFragment = new PlaceholderFragment();
            final String backStateName = mHomeFragment.getClass().getName();
            final Dbhandler dbHandler = new Dbhandler();

            Button logout = (Button) rootView.findViewById(R.id.logout);
            final TextView uName = (TextView) rootView.findViewById(R.id.USername);
            final String name = dbHandler.getisOnline();
            uName.setText("Welcome-" + name);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new AddMemo())
                            .addToBackStack(backStateName)
                            .commit();


                }
            });
            Users.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new MemoReport())
                            .addToBackStack(backStateName)
                            .commit();


                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            Memo.getContext());

                    // set title
                    alertDialogBuilder.setTitle("Do you want to Logout?");

                    // set dialog message
                    alertDialogBuilder

                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage("Do you want to Logout?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dbHandler.updateUserLogout(name);
                                    startActivity(new Intent(Memo.getContext(), Login.class));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                    //finish();
                }


            });
            return rootView;
        }

    }

    public void onBackPressed() {

//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
//
//            Orderfragment demandDetails =  (Orderfragment) getSupportFragmentManager().findFragmentByTag(tag);
//            getSupportFragmentManager().popBackStack();
//
//            return;
//        }
//        super.onBackPressed();

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack(getSupportFragmentManager().getBackStackEntryAt(0).getId(), getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }

    public void onFragmentInteraction(Uri uri) {

    }
}