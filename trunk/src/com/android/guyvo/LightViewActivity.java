package com.android.guyvo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import static com.android.guyvo.MainActivity.triosModel;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 30, 2010
 * Time: 7:22:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class LightViewActivity extends Activity implements AdapterView.OnItemClickListener {

    LightAdapter lightAdapter;
    CortexAdapter cortexAdapter;

    Gallery galleryLights;
    Gallery galleryCortexes;

    SliderView sliderView;

    private void getItems() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_lights);
        galleryLights = (Gallery) findViewById(R.id.gallery1);
        galleryCortexes = (Gallery) findViewById(R.id.gallery2);
        sliderView = (SliderView) findViewById(R.id.slider);
        sliderView.setEnabled(true);
        lightAdapter = new LightAdapter(this);
        cortexAdapter = new CortexAdapter(this);
        galleryLights.setAdapter(lightAdapter);
        galleryCortexes.setAdapter(cortexAdapter);
        galleryLights.setOnItemClickListener(this);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        triosModel.sortOnLightValue();
        triosModel.sortOnCortexSensor();
        lightAdapter.notifyDataSetChanged();
        cortexAdapter.notifyDataSetChanged();

        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listlights, menu);
        MenuItem menuItem = menu.getItem(1);
        menuItem.setIntent(new Intent(this, TriosXmlTextView.class));
        
        return true;
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Light light;
        light = (Light) lightAdapter.getItem(i);
        Toast.makeText(this, light.getValue(), Toast.LENGTH_SHORT).show();
        sliderView.setValue(Integer.valueOf(light.getValue()));
    }

    public class LightAdapter extends BaseAdapter implements View.OnFocusChangeListener {
        private Context context;
        private int itemBackground;


        public LightAdapter(Context c) {
            context = c;
            //---setting the style---
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
            itemBackground = a.getResourceId(
                    R.styleable.Gallery1_android_galleryItemBackground, 0);
            a.recycle();
        }

        public int getCount() {
            return triosModel.getListLights().size();
        }

        public Object getItem(int position) {
            return triosModel.getListLights().get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(context);

            if (convertView == null) {
                textView.setTextSize(10);
                textView.setText("Value : " + triosModel.getListLights().get(position).getValue());
                textView.setLayoutParams(new Gallery.LayoutParams(80, 80));
                textView.setTextColor(0xFFFF0000);
                textView.setBackgroundColor(0xFF202050);
                textView.setOnFocusChangeListener(this);
                Log.d("LightViewActivity","Galerry adapter view null");

                //textView.setBackgroundResource(itemBackground);
            } else {
                textView = (TextView) convertView;
            }
            return textView;
        }

        public void onFocusChange(View view, boolean b) {
            if (b)
                view.setBackgroundColor(0xFF808090);
            else
                view.setBackgroundColor(0xFF202020);
        }
    }

    public class CortexAdapter extends BaseAdapter implements View.OnFocusChangeListener {
        private Context context;
        private int itemBackground;

        public CortexAdapter(Context c) {
            context = c;
            //---setting the style---
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
            itemBackground = a.getResourceId(
                    R.styleable.Gallery1_android_galleryItemBackground, 0);
            a.recycle();
        }

        public int getCount() {
            return triosModel.getListCortexes().size();
        }

        public Object getItem(int position) {
            return triosModel.getListCortexes().get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            if (convertView == null) {
                TextView textView = new TextView(context);
                TextView textView2 = new TextView(context);
                textView.setTextSize(10);
                textView2.setTextSize(10);
                textView.setTextColor(Color.YELLOW);
                textView2.setTextColor(Color.YELLOW);
                textView.setText("Temp : " + triosModel.getListCortexes().get(position).getSensor());
                textView2.setText("Dims : " + triosModel.getListCortexes().get(position).getDimmer());
                linearLayout.setLayoutParams(new Gallery.LayoutParams(80, 80));
                linearLayout.setBackgroundColor(Color.GRAY);
                linearLayout.addView(textView);
                linearLayout.addView(textView2);
                linearLayout.setOnFocusChangeListener(this);
                //textView.setBackgroundResource(itemBackground);
            } else {

                linearLayout = (LinearLayout) convertView;
            }
            return linearLayout;
        }

        public void onFocusChange(View view, boolean b) {
            if (b)
                view.setBackgroundColor(0xFF808090);
            else
                view.setBackgroundColor(0xFF202050);
        }
    }
}
