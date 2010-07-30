package com.android.guyvo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 17, 2010
 * Time: 5:50:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity implements Runnable {
    private TextView textView;
    private EditText nameUrl;
    private ProgressDialog m_ProgressDialog;

    public static TriosModel triosModel;
    public static TriosXmlFeed triosXmlFeed;

    NotificationManager mManager;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            if (message.what == 0) {
                m_ProgressDialog.dismiss();
            }
        }
    };

    @Override
    public Object onRetainNonConfigurationInstance() {
        final TriosModel model = triosModel;
        return model;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        nameUrl = (EditText) findViewById(R.id.urlname);
        textView = (TextView) findViewById(R.id.text);
        nameUrl.setText(R.string.defaulturl);
        mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        try {
            final TriosModel data = (TriosModel) getLastNonConfigurationInstance();
            if (data == null) {
                Thread t = new Thread(this);
                t.setName("IOthread");
                t.start();
                m_ProgressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Retrieving data ...", true);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        MenuItem menuItem = menu.getItem(2);
        menuItem.setIntent(new Intent(this, SetupActivity.class));
        menuItem = menu.getItem(0);
        menuItem.setIntent(new Intent(this, LightViewActivity.class));
        menuItem = menu.getItem(1);
        menuItem.setIntent(new Intent(this, SliderTest.class));
        menuItem = menu.getItem(3);
        menuItem.setIntent(new Intent(this, TriosListPrograms.class));
        return true;
    }

    public void run() {

        try {
            triosModel = new TriosModel();
            triosXmlFeed = new TriosXmlFeed(nameUrl.getText().toString());
            triosModel.registerChangeEvents(triosXmlFeed);
            triosXmlFeed.getXml();
            handler.sendEmptyMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
