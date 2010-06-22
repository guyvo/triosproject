package com.android.guyvo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.Menu;
import android.view.MenuItem;
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

	public static String toText (InputStream in) throws IOException {
	    StringBuffer out = new StringBuffer();
	    byte[] b = new byte[4096];
	    for (int n; (n = in.read(b)) != -1;) {
	        out.append(new String(b, 0, n));
	    }
	    return out.toString();
	}

	public static InputStream getInputStreamFromUrl(String url, Context ctx) {
		InputStream content = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
    

		try {
			HttpGet httpGet = new HttpGet(url);
 			httpGet.addHeader("cortex", "4");
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpGet);
			content = response.getEntity().getContent();
            String text = toText(content);
            out.write(text.getBytes());
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(text);
            stringEntity.setContentType("text/xml");
            httpPost.setEntity(stringEntity);
            httpPost.addHeader("cortex", "4");
            response = httpclient.execute(httpPost);
            Toast.makeText(ctx, response.getLastHeader("status").getValue(), Toast.LENGTH_SHORT ).show();
		} catch (Exception e) {
			// handle the exception !
			Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG ).show();
			Log.e("GUYVO",e.getMessage());
		}

		return content;
	}

    @Override
    public Object onRetainNonConfigurationInstance() {
        final List<Light> data = listLights;
        return data;
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		byte[] b = new byte[4096];
		int len;

		super.onStart();

		try {
			InputStream inputStream = getInputStreamFromUrl("http://guyvo.no-ip.biz:60000/",this);
			/*if (inputStream !=null){
				//len = inputStream.read(b, 0, 3241);
				//text.setText(toText(inputStream));
				XmlParser xml = new XmlParser(inputStream);
				xml.addXmlParseListener(this);
				xml.Parse();
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG );
			Log.e("GUYVO",e.getMessage());
		}

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
