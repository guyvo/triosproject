package com.android.guyvo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 17, 2010
 * Time: 5:50:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    TextView textView;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main , menu);
        return true;
    }
}
