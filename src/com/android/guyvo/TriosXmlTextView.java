package com.android.guyvo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

import static com.android.guyvo.MainActivity.triosModel;
import static com.android.guyvo.MainActivity.triosXmlFeed;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 17, 2010
 * Time: 8:59:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosXmlTextView extends Activity {
    TextView xml;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlview);
        xml = (TextView) findViewById(R.id.xmltext);
        xml.setText(triosModel.toXml());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.xmlpost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try {
            triosXmlFeed.postXml(xml.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
