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
public class Login extends Activity {
        public static String base64;
        private static final String TAG = "Login";
        @Override public void onResume() { super.onResume(); Memo.setContext(this); }
        @Override public void onPause() { super.onPause(); Memo.setContext(null); }
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Memo.setContext(this);
                setContentView(R.layout.login);
                final EditText uid, password;
                final TextView regLink;
                uid = (EditText) findViewById(R.id.input_UserName);
                password = (EditText) findViewById(R.id.input_password);
                final Button Login = (Button) findViewById(R.id.btn_login);
                regLink = (TextView)findViewById(R.id.link_signup);

                regLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                startActivity(new Intent(Login.this, Registration.class));
                        }
                });
                Login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                try {
                                        Dbhandler dbHandler = new Dbhandler();


                                        if (dbHandler.getAuthorizin(uid.getText().toString(), password.getText().toString()))
                                        {

                                                Toast.makeText(Memo.getContext(), "Login Successful", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(Login.this, MainActivity.class));
                                                dbHandler.updateUserSession(uid.getText().toString());
                                                finish();

                                        }
                                        else
                                        {
                                                Toast.makeText(Memo.getContext(), "invalied", Toast.LENGTH_LONG).show();
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
