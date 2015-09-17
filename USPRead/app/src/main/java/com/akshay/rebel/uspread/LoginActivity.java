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


public class LoginActivity extends ActionBarActivity {

    private String TAG = "login activity";

    Button btnLogin;
    Button  btnForgotPass;
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

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static final String KEY_UID = "user_id";

    private static final String KEY_FNAME = "user_fname";
    private static final String KEY_LNAME = "user_lname";

    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_DOB = "user_dob";
    private static final String KEY_GENDER = "user_gender";
    private static final String KEY_ADDRESS = "user_address";
    private static final String KEY_CONTACT = "user_contact";
    private static final String KEY_ROLE = "user_role";




    String fname,lname,email, u_id, gender, dob, address, contact,userrole;


    // User Session Manager Class
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cd = new ConnectionDetector(getApplicationContext());

        session = new UserSessionManager(getApplicationContext());

        final EditText txtUsername = (EditText) findViewById(R.id.accessID);
        final EditText txtPassword = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        loginErrorMsg = (TextView) findViewById(R.id.msg_error);
        ForgetPass = (TextView) findViewById(R.id.tvforgot_pass);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_id = txtUsername.getText().toString();
                password = txtPassword.getText().toString();

                if ((u_id.length()) > 0 && (password.length()) > 0) {

                    new LongOperation().execute("");// start login task

                } else {

                    AlertDialog.Builder adb = new AlertDialog.Builder(
                            LoginActivity.this);
                    adb.setTitle("Missing Data");
                    adb.setMessage("Missing Email and/or Password");
                    adb.setPositiveButton("Ok", null);
                    adb.show();

                }
            }
        });

        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),
                        ForgotPassword.class);
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


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.i("long operation..", TAG);
            String status = "internet";

            isInternetPresent = cd.isConnectingToInternet();

            if (isInternetPresent) {

                JSONObject json = userFunction.loginUser(u_id, password);// check

                try {
                    if (json.getString(KEY_SUCCESS) != null) {// if user found
                        // then
                        // login

                        status = "Success";
                        Log.i("user found..", TAG);
                        String res = json.getString(KEY_SUCCESS);
                        if (Integer.parseInt(res) == 1) {
                            // user successfully logged in
                            // Store user details in SQLite Database
                            Log.i("success found..", TAG);
                            JSONObject json_user = json.getJSONObject("user");

                            // Clear all previous data in database


                            u_id = json.getString(KEY_UID);
                            fname = json_user.getString(KEY_FNAME);
                            gender = json_user.getString(KEY_GENDER);
                            dob = json_user.getString(KEY_DOB);
                            address = json_user.getString(KEY_ADDRESS);
                            lname = json_user.getString(KEY_LNAME);
                            contact = json_user.getString(KEY_CONTACT);
                            userrole = json_user.getString(KEY_ROLE);
                            email = json_user.getString(KEY_EMAIL);

                            Log.i("id", u_id);

                        } else {
                            // incorrect username and/or password
                            status = "fail";
                            Log.i("login fail", "fail");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();// error
                    status = "error";
                    Log.i("login error", "error");
                }
            } else {
                // Internet connection is not present
                // Ask user to connect to Internet
                // showAlertDialog(AndroidDetectInternetConnectionActivity.this,
                // "No Internet Connection",
                // "You don't have internet connection.", false);
                //error.setText("No internet Access");
                Log.i("er", "er");
                runOnUiThread(new Runnable() {
                    public void run() {
                        loginErrorMsg.setText("You need to be connected to the Internet in order to login.");
                    }
                });
            }
            return status;// return login status
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contentEquals("Success")) {




                loginErrorMsg.setText("Logging In...");// inform user on
                session.createUserLoginSession(fname+" "+lname, email,u_id,dob,gender,address);

                Intent i = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(i);
                finish();



            } else if (result.contentEquals("fail")) {
                loginErrorMsg.setText("Incorrect username/password");
                Log.i("Incorrect username/password","fail");
            } else if (result.contentEquals("error")) {

                loginErrorMsg.setText("Oops! Seems like we have encountered an error!");
                Log.i("Oops! Seems like we have encountered an error!","error");
            } else {
                loginErrorMsg.setText("You need to be connected to the Internet in order to login.");
                Log.i("You need to be connected to the Internet in order to login.","else erro");
            }
        }

        @Override
        protected void onPreExecute() {
            // before logging in

        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
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
}
