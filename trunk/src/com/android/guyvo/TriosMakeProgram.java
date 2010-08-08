package com.android.guyvo;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import static com.android.guyvo.MainActivity.triosModel;
import static com.android.guyvo.MainActivity.triosXmlFeed;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 31, 2010
 * Time: 1:42:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosMakeProgram extends ListActivity {
    ListView listView;
    Spinner spinner;

    TriosProgramValues triosProgramValues;

    ProgramAdapter programAdapter;

    private List<Light> listLights;

    private static class ProgramAdapter extends BaseAdapter {
        Context context;

        public ProgramAdapter(Context context) {
            this.context = context;
        }

        public int getCount() {
            return triosModel.getListLights().size();
        }

        public Object getItem(int i) {
            return triosModel.getListLights().get(i);
        }

        public long getItemId(int i) {
            return i;
        }

        static class ViewHolder{
            TextView textView;
            SliderView sliderView;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder myview;

            if (view == null) {
                
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.programrow, null);

                myview = new ViewHolder();
                myview.sliderView = (SliderView) view.findViewById(R.id.LIGHT_SLIDER);
                myview.textView = (TextView) view.findViewById(R.id.LIGHT_NAME);
                myview.sliderView.addSliderViewListner(triosModel);
                myview.textView.setTextColor(0xFFAA3040);
                myview.textView.setTextSize(10);
                myview.textView.setBackgroundColor(0xFF202520);

                view.setTag(myview);
            } else {
                myview = (ViewHolder)view.getTag();
            }

            myview.textView.setText(triosModel.getListLights().get(i).getName());
            myview.sliderView.setValue(triosModel.getListLights().get(i).getViewValue(), i);

            return view;
        }

    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater menuInflater = getMenuInflater();
         menuInflater.inflate(R.menu.xmlpost, menu);
         return true;
     }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        triosModel.sortOnCortexSensor();
        programAdapter.notifyDataSetChanged();

        try {
            triosXmlFeed.postXml(triosModel.toXml());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = getListView();
        setContentView(listView);
        programAdapter = new ProgramAdapter(this);
        listView.setAdapter(programAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
