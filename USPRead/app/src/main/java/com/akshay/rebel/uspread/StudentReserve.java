package com.akshay.rebel.uspread;

/**
 * Created by Rebel on 9/15/2015.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


public class StudentReserve extends Fragment {

    private String TAG = "student reserve";

    TextView title, due, catnum;
    ListView reserve_list;

    private static String KEY_SUCCESS = "success";
    private static final String KEY_BK_TITLE = "bk_title";
    private static final String KEY_PICKUPDATE= "pickupDate";
    private static final String KEY_BKCATNUM = "bkCatNum";

    final UserApi userApi = new UserApi();

    ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();

    Boolean isInternetPresent = false;

    ConnectionDetector cd;

    JSONObject jsonreserve;
    View view;
    TextView error;
    ImageView networkImage;
    UserSessionManager session;
    String user_Id;
    public StudentReserve()
    {

    }

    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        view = inflater.inflate(R.layout.student_bk_reserve, container, false);

        cd = new ConnectionDetector(getActivity().getApplicationContext());

        //getting logged in student ID from session
        session = new UserSessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        user_Id = user.get(UserSessionManager.KEY_USERID);


        reserve_list = (ListView) view.findViewById(R.id.ReserveList);
        error = (TextView) view.findViewById(R.id.network);
        error.setVisibility(View.INVISIBLE);
        oslist = new ArrayList<HashMap<String, String>>();
        new LongOperation().execute("");

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
                jsonreserve = userApi.StudentReserve(user_Id);
                try {
                    if (jsonreserve.getString(KEY_SUCCESS) != null) {// if user found then
                        // login
                        status = "Success";
                        Log.i("user found..", TAG);
                        String res = jsonreserve.getString(KEY_SUCCESS);
                        if (Integer.parseInt(res) == 1) {
                            // user successfully logged in
                            // Store user details in SQLite Database
                            Log.i("success found..", TAG);
                            JSONArray jsonGet = jsonreserve.getJSONArray("reserve");

                            for(int i=0;i<jsonGet.length();i++){

                                JSONObject fetch_event = jsonGet.getJSONObject(i);
                                // Storing  JSON item in a Variable
                                String bk_title = fetch_event.getString(KEY_BK_TITLE);
                                String pickupDate = fetch_event.getString(KEY_PICKUPDATE);
                                String bkCatNum = fetch_event.getString(KEY_BKCATNUM);

                                // Adding value HashMap key => value
                                HashMap<String, String> map = new HashMap<String, String>();

                                map.put(KEY_BK_TITLE, bk_title);
                                Log.i(KEY_BK_TITLE + " "+pickupDate , bk_title);
                                map.put(KEY_PICKUPDATE, pickupDate);
                                map.put(KEY_BKCATNUM, bkCatNum);
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

                if (jsonreserve.length() ==0){
                    //Log.i("empty", "events");
                    Toast.makeText(getActivity().getApplicationContext(), "There are no Books on reserve", Toast.LENGTH_SHORT).show();
                }

                ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), oslist,
                        R.layout.student_bk_reserve2,
                        new String[] { KEY_BK_TITLE, KEY_PICKUPDATE,KEY_BKCATNUM }, new int[] {
                        R.id.res_title, R.id.res_pickdate,
                        R.id.res_catnum});
                reserve_list.setAdapter(adapter);

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
