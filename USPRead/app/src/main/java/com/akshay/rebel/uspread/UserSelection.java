package com.akshay.rebel.uspread;

/**
 * Created by Akshay on 9/8/2015.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Controller.ConnectionDetector;
import Controller.UserApi;
import Controller.UserSessionManager;


public class UserSelection extends ActionBarActivity {

    private String TAG = "login activity";

    Button btnLogin;
    Button  btnForgotPass, stud, clerk;
    Button gallery, news;
    EditText inputEmail;
    EditText inputPassword;
    TextView loginErrorMsg,ForgetPass;

    String password;
    final UserApi userFunction = new UserApi();
    private ProgressDialog progressDialog;

    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;




    // User Session Manager Class
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_selection);

        cd = new ConnectionDetector(getApplicationContext());

        stud = (Button) findViewById(R.id.student);
        clerk = (Button) findViewById(R.id.clerk);






        stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        clerk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),
                        LoginActivity2.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


//    private class LongOperation extends AsyncTask<String, Void, String> {
//
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result.contentEquals("Success")) {
//
//
//
//
//                loginErrorMsg.setText("Logging In...");// inform user on
//                session.createUserLoginSession(fname+" "+lname, email,u_id,dob,gender,address);
//
//                Intent i = new Intent(getApplicationContext(),
//                        MainActivity.class);
//                startActivity(i);
//                finish();
//
//
//
//            } else if (result.contentEquals("fail")) {
//                loginErrorMsg.setText("Incorrect username/password");
//                Log.i("Incorrect username/password","fail");
//            } else if (result.contentEquals("error")) {
//
//                loginErrorMsg.setText("Oops! Seems like we have encountered an error!");
//                Log.i("Oops! Seems like we have encountered an error!","error");
//            } else {
//                loginErrorMsg.setText("You need to be connected to the Internet in order to login.");
//                Log.i("You need to be connected to the Internet in order to login.","else erro");
//            }
//        }
//
//        @Override
//        protected void onPreExecute() {
//            // before logging in
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//
//        }
//    }

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
}
