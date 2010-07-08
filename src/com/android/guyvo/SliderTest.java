package com.android.guyvo;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 7, 2010
 * Time: 8:14:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SliderTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SliderView sliderView = new SliderView(this);
        setContentView(sliderView);
    }
}
