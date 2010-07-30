package com.android.guyvo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import static com.android.guyvo.MainActivity.triosModel;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 29, 2010
 * Time: 7:40:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class CortexList extends ListActivity {
    ArrayList<String> items = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    private void getItems() {
        items.clear();
        for (int i = 0; i < triosModel.getListLights().size(); i++) {
            items.add(triosModel.getListLights().get(i).getValue());
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cortexlist);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.lightlist_value, items);
        getItems();
        setListAdapter(arrayAdapter);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        triosModel.sortOnLightValue();
        getItems();
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listlights, menu);
        return true;
    }

}
