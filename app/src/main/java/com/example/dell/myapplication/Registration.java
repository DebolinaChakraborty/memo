package com.example.dell.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication.Dbhandler.Dbhandler;

/**
 * Created by DELL on 5/21/2016.
 */
public class Registration extends Activity {
    public static String base64;
    private static final String TAG = "Login";
    @Override public void onResume() { super.onResume(); Memo.setContext(this); }
    @Override public void onPause() { super.onPause(); Memo.setContext(null); }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Memo.setContext(this);
        setContentView(R.layout.reg);
        final EditText name,uName, password;
        name = (EditText) findViewById(R.id.input_name);
        uName = (EditText) findViewById(R.id.input_UName);
        password = (EditText) findViewById(R.id.input_password);
        final Button signUP = (Button) findViewById(R.id.btn_signup);
        final TextView tvLogin = (TextView) findViewById(R.id.link_login);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, Login.class));
            }
        });
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Dbhandler dbHandler = new Dbhandler();
                    if(dbHandler.getUserName(uName.getText().toString()))
                    {
                        Toast.makeText(Memo.getContext(), "UserName Alredy Exists", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        loginBO log = new loginBO();
                        log.setName(name.getText().toString());
                        log.setPassword(password.getText().toString());
                        log.setUserName(uName.getText().toString());
                        dbHandler.insertUserDetails(log);
                        Toast.makeText(Memo.getContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Registration.this, Login.class));
                        finish();
                    }
                }
                catch (Exception ex)
                {
                    ex.getMessage();
                }

            }
        });
    }



}


