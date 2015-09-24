package com.akshay.rebel.uspread;

/**
 * Created by Rebel on 9/15/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import Controller.ConnectionDetector;
import Controller.UserApi;
import Controller.UserSessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class StudentSearch extends Fragment {

    private String TAG = "student search";

    TextView hello, due, catnum;
    ListView onloan_list;
    EditText keyword;
    EditText subject;
    Button find;

    private static String KEY_SUCCESS = "success";
    private static final String KEY_BK_TITLE = "bk_title";
    private static final String KEY_BK_AUTHOR = "bk_author";
    private static final String KEY_BK_EDITION = "bk_edition";
    private static final String KEY_BKCATNUM = "bkCatNum";
    private static final String KEY_COPYSTAT_DESC = "copyStat_desc";

    final UserApi userApi = new UserApi();

    ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();

    Boolean isInternetPresent = false;

    ConnectionDetector cd;

    JSONObject jsonsearch;
    View view;
    TextView error;
    ImageView networkImage;
    UserSessionManager session;
    String user_Id;
    String bk_title;
    EditText title;
    public StudentSearch()
    {

    }



    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        view = inflater.inflate(R.layout.student_bk_search, container, false);

        cd = new ConnectionDetector(getActivity().getApplicationContext());

        //getting logged in student ID from session
        session = new UserSessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        user_Id = user.get(UserSessionManager.KEY_USERID);
        title = (EditText) view.findViewById(R.id.inputSearch);

        subject = (EditText) view.findViewById(R.id.inputSearch);
        find = (Button) view.findViewById(R.id.search);


        find.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                bk_title = subject.getText().toString();

                oslist.clear();
                new LongOperation().execute("");

                //new onPostExecute().execute("");
            }
        });



        onloan_list = (ListView) view.findViewById(R.id.list_view);
        error = (TextView) view.findViewById(R.id.network);
        error.setVisibility(View.INVISIBLE);
        oslist = new ArrayList<HashMap<String, String>>();
        //new LongOperation().execute("");

        view.setFocusableInTouchMode(true);
        view.requestFocus();

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                }
                return false;
            }
        });
        return view;

    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        //	private ProgressDialog pDialog;

        @Override
        protected String doInBackground(String... params) {
            Log.i("long operation..", TAG);
            String status = "internet";

            // email
            isInternetPresent = cd.isConnectingToInternet();
            if(isInternetPresent){
                jsonsearch = userApi.StudentSearchBk(user_Id, bk_title);
                try {
                    if (jsonsearch.getString(KEY_SUCCESS) != null) {// if user found then
                        // login
                        status = "Success";
                        Log.i("user found..", TAG);
                        String res = jsonsearch.getString(KEY_SUCCESS);
                        if (Integer.parseInt(res) == 1) {
                            // user successfully logged in
                            // Store user details in SQLite Database
                            Log.i("success found..", TAG);
                            JSONArray jsonGet = jsonsearch.getJSONArray("list");

                            for(int i=0;i<jsonGet.length();i++){

                                JSONObject fetch_event = jsonGet.getJSONObject(i);
                                // Storing  JSON item in a Variable
                                String bk_title = fetch_event.getString(KEY_BK_TITLE);
                                String bk_author = fetch_event.getString(KEY_BK_AUTHOR);
                                String bk_edition = fetch_event.getString(KEY_BK_EDITION);
                                String bkCatNum = fetch_event.getString(KEY_BKCATNUM);
                                String copyStat_desc = fetch_event.getString(KEY_COPYSTAT_DESC);

                                // Adding value HashMap key => value
                                HashMap<String, String> map = new HashMap<String, String>();

                                map.put(KEY_BK_TITLE, bk_title);
                                Log.i(KEY_BK_TITLE + " " + bk_author, bk_title);
                                map.put(KEY_BK_AUTHOR, bk_author);
                                map.put(KEY_BK_EDITION, bk_edition);
                                map.put(KEY_BKCATNUM, bkCatNum);
                                map.put(KEY_COPYSTAT_DESC, copyStat_desc);
                                oslist.add(map);
                            }

                        } else {
                            // incorrect username and/or password
                            status = "fail";
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();// error
                    status = "error";
                }
            }else{
                Log.i("er", "er");
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        error.setVisibility(View.VISIBLE);
                        //networkImage.setVisibility(View.VISIBLE);
                        error.setText("Unable to connect to the network");
                    }
                });
            }



            return status;// return login status
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contentEquals("Success")) {

                if (jsonsearch.length() ==0){
                    //Log.i("empty", "events");
                    Toast.makeText(getActivity().getApplicationContext(), "Sorry no books found", Toast.LENGTH_SHORT).show();
                }

                ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), oslist,
                        R.layout.student_bk_search2,
                        new String[] { KEY_BK_TITLE, KEY_BK_AUTHOR,KEY_BK_EDITION,KEY_BKCATNUM,KEY_COPYSTAT_DESC }, new int[] {
                        R.id.loan_title, R.id.author, R.id.edition,
                        R.id.loan_catnum, R.id.status});
                onloan_list.setAdapter(adapter);
                onloan_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        // TODO Auto-generated method stub

                        for (int i = 0; i < onloan_list.getCount(); i++) {
                            if (position == i) {
                                Intent dash = new Intent(getActivity()
                                        .getApplicationContext(),
                                        StudentBookDetails.class);
                                dash.putExtra("bk_title", oslist.get(position)
                                        .get(KEY_BK_TITLE));
                                dash.putExtra(
                                        "bk_author",
                                        oslist.get(position).get(
                                                KEY_BK_AUTHOR));
                                dash.putExtra(
                                        "bk_edition",
                                        oslist.get(position).get(
                                                KEY_BK_EDITION));
                                dash.putExtra("bkCatNum", oslist.get(position)
                                        .get(KEY_BKCATNUM));
                                dash.putExtra("copyStat_desc", oslist.get(position)
                                        .get(KEY_COPYSTAT_DESC));

                                startActivity(dash);

                            }
                        }

                    }
                });
                //onloan_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

//                    @Override
//                    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//                                            long arg3) {
//                        // TODO Auto-generated method stub
//
//
//                        for (int i=0;i<videolist.getCount();i++){
//                            if (position ==i){
//                                Intent dash = new Intent(getActivity().getApplicationContext(),
//                                        Video_get.class);
//                                dash.putExtra("vid_id", oslist.get(position).get(KEY_VIDID));
//                                dash.putExtra("vid_url_id",oslist.get(position).get(KEY_VID_URL_ID));
//
//                                startActivity(dash);
//
//                            }
//                        }
//
//
//
//                    }
                // });



            } else if (result.contentEquals("fail")) {

            } else if (result.contentEquals("error")) {


            } else {

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


}
