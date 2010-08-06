package com.android.guyvo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 30, 2010
 * Time: 8:38:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosListPrograms extends Activity implements AdapterView.OnItemSelectedListener {
    Properties properties = new Properties();
    File file = new File("/sdcard/trios/");
    Spinner spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programs);
        properties.setProperty("1","guy");
        properties.setProperty("2","guy");
        properties.setProperty("3","guy");
        properties.setProperty("4","guy");

        /*
        try {

            //file.mkdirs();

            OutputStream outputStream = new FileOutputStream("/sdcard/trios/program1.xml");
            properties.storeToXML(outputStream,"comment");
            outputStream.flush();
            outputStream.close();
            OutputStream outputStream1 = new FileOutputStream("/sdcard/trios/program2.xml");
            properties.storeToXML(outputStream1,"comment");
            outputStream1.flush();
            outputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        spinner = (Spinner)this.findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,file.list());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.programs, menu);
        MenuItem menuItem = menu.getItem(0);
        menuItem = menu.getItem(0);
        menuItem.setIntent(new Intent(this, TriosMakeProgram.class));
        return true;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, (String)spinner.getSelectedItem(), Toast.LENGTH_SHORT).show();

    }

    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
