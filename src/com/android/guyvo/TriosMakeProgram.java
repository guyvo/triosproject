package com.android.guyvo;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


import java.util.*;

import static com.android.guyvo.MainActivity.triosModel;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 31, 2010
 * Time: 1:42:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosMakeProgram extends ListActivity implements AdapterView.OnItemSelectedListener {
    ListView listView;
    Spinner spinner;

    Properties properties = new Properties();

    TriosProgramValues triosProgramValues;

    ProgramAdapter programAdapter;

    private List<Light> listLights;


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        /*
        SliderView sliderView = (SliderView)view.findViewById(R.id.LIGHT_SLIDER);
        values.set(i,sliderView.getValue());
        if (values.size() < 20) programAdapter.Add(50);
        Log.d("TriosMakeProgram","Onselectitem");
        */
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private class ProgramAdapter extends BaseAdapter  {
        Context context;

        public ProgramAdapter (Context context){
           this.context = context;
        }

        public void Add (int val){
           notifyDataSetChanged();
        }

        public int getCount() {
           return triosProgramValues.getSize();
        }

        public Object getItem(int i) {
            return triosProgramValues.getValue(i);
        }

        public long getItemId(int i) {
            return i;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            Log.d("TriosMakeProgram","notify data");
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View myview;

            if (view == null)  {
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myview = inflater.inflate(R.layout.programrow,null);
                SliderView sliderView = (SliderView)myview.findViewById(R.id.LIGHT_SLIDER);
                sliderView.addSliderViewListner(triosProgramValues);
            }else{
                myview = view;
            }

            TextView textView = (TextView) myview.findViewById(R.id.LIGHT_NAME);
            textView.setText("name");
            SliderView sliderView = (SliderView)myview.findViewById(R.id.LIGHT_SLIDER);
            sliderView.setValue((Integer) triosProgramValues.getValue(i),i);

            return myview;
        }

    }

    private void makeInitdata( ){
        triosProgramValues = new TriosProgramValues(10);
        for (int i=0; i < 10 ;i++ ){
            triosProgramValues.addValue(100,i);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = getListView();
        setContentView(listView);
        makeInitdata();
        //listView = (ListView) findViewById(R.id.LIST_NAMES);
        listView.setOnItemSelectedListener(this);

        programAdapter = new ProgramAdapter(this);
        programAdapter.notifyDataSetChanged();
        listView.setAdapter(programAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
