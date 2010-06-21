package com.android.guyvo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 17, 2010
 * Time: 5:50:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    TextView textView;
    public static List<Light> listLights = new ArrayList<Light>();
    public static List<Cortex> listCortexes = new ArrayList<Cortex>();

    @Override
    public Object onRetainNonConfigurationInstance() {
        final List<Light> data = listLights;
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final List<Light> data = (List<Light>) getLastNonConfigurationInstance();
        if ( data == null){
            //TODO add http get parse xml here
            Light light = new Light("", "", "");
            listLights.add(light);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        MenuItem menuItem = menu.getItem(2);
        menuItem.setIntent(new Intent(this, Setup.class));
        return true;
    }
}
