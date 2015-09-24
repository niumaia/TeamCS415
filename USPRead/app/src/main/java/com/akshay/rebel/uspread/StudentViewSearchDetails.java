package com.akshay.rebel.uspread;

/**
 * Created by Rebel on 9/24/2015.
 */


import java.util.HashMap;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class StudentViewSearchDetails extends Fragment {

    private String TAG = "View News";
    View view;
    Button reserve;
    TextView res_title, res_auth, res_edit,res_cat,res_stat;
    String title,author,edition,cat,stat;
    HashMap<String, String> data;
    TextView t;
//
//@Override
//    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//
//        view = inflater.inflate(R.layout.student_reservebk, container, false);
//
//        Intent dash = getIntent();
//        title = dash.getStringExtra("bk_title");
//        author = dash.getStringExtra("bk_author");
//        edition = dash.getStringExtra("bk_edition");
//        cat = dash.getStringExtra("bkCatNum");
//        stat = dash.getStringExtra("copyStat_desc");
//
//        res_title = (TextView) view.findViewById(R.id.res_title);
//        res_auth = (TextView) view.findViewById(R.id.res_auth);
//        res_edit = (TextView) view.findViewById(R.id.res_edit);
//        res_cat = (TextView) view.findViewById(R.id.res_cat);
//        res_stat = (TextView) view.findViewById(R.id.res_stat);
//
//
//
//        //setting textview.
//        res_title.setText(title);
//        res_auth.setText(author);
//        res_edit.setText(edition);
//        res_cat.setText(cat);
//        res_stat.setText(stat);
//        return view;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                //Intent i = new Intent(getApplicationContext(), News.class);
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    //back button
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        if(keyCode==KeyEvent.KEYCODE_BACK)
//
//        {
//            Intent dash = new Intent(getApplicationContext(),
//                    StudentSearch.class); //change class to activity u want to go back to.
//        }
//        return true;
//    }
//



}


