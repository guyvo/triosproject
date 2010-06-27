package com.android.guyvo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 17, 2010
 * Time: 5:50:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity implements TriosLightChangeListener,TriosCortexChangeListener{
    private TextView textView;
    private EditText nameUrl;

    public static List<Light> listLights = new ArrayList<Light>();
    public static List<Cortex> listCortexes = new ArrayList<Cortex>();
    public static TriosXmlFeed triosXmlFeed;

    @Override
    public Object onRetainNonConfigurationInstance() {
        final List<Light> data = listLights;
        return data;
    }

	@Override
	protected void onStart() {
		super.onStart();

		try {

            triosXmlFeed.getXml();
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG ).show();
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        nameUrl = (EditText)findViewById(R.id.urlname);
        nameUrl.setText(R.string.defaulturl);
        try {
            triosXmlFeed = new TriosXmlFeed(nameUrl.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        triosXmlFeed.addTriosCortesChangeListener(this);
        triosXmlFeed.addTriosLightChangeListener(this);


        final List<Light> data = (List<Light>) getLastNonConfigurationInstance();
        if ( data == null){

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

    public void onLightChange(LightChangeEvent e) {
        boolean found = false;
        Iterator it = listLights.listIterator();

        while (it.hasNext()){
            Light light = (Light) it.next();
            if (
                 (light.getCortexId().compareTo(e.getLight().getCortexId()) == 0) &&
                 (light.getLightId().compareTo(e.getLight().getLightId()) == 0)
               ){
                listLights.remove(light);
                listLights.add(e.getLight());
                found = true;
                break;
            }
        }

        if (! found){
             listLights.add(e.getLight());
        }
     }

    public void onCortexChange(CortexChangeEvent e) {
        boolean found = false;
        Iterator it = listCortexes.iterator();

        while (it.hasNext()){
            Cortex cortex = (Cortex) it.next();
            if ( cortex.getName().compareTo(e.getCortex().getName()) == 0 ){
                listCortexes.remove(cortex);
                listCortexes.add(e.getCortex());
                found = true;
                break;
            }
        }

        if (! found){
            listCortexes.add(e.getCortex());
        }
    }
}
