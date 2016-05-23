package com.example.dell.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.dell.myapplication.Dbhandler.Dbhandler;

/**
 * Created by DELL on 5/21/2016.
 */
public class Splash extends Activity {
    private static long SLEEP_TIME = 3;

    @Override public void onResume() { super.onResume(); Memo.setContext(this); }
    @Override public void onPause() { super.onPause(); Memo.setContext(null); }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Memo.setContext(this);
        setContentView(R.layout.splash);
        new IntentLauncher().start();

//dbHandler.deleteDatabase(); // TODO remove this test line!!!
        // new dbHandler().getReadableDatabase().close();
//        if (Utils.isOnline(this)) {
//            new IntentLauncher().start();
//        }
    }
    private class IntentLauncher extends Thread {
        public void run() {
            try { sleep(SLEEP_TIME * 1000); } catch (Exception e) {;}
            Dbhandler dbHandler = new Dbhandler();

            boolean val = dbHandler.getLoggedin();
            if(val)
            {
                startActivity(new Intent(Splash.this, MainActivity.class));
            }
            else
            {
                startActivity(new Intent(Splash.this, Login.class));
            }

            finish();

        }
    }
}


