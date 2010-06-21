package com.android.guyvo;

import android.app.Activity;
import android.os.Bundle;
import java.util.Collections;
import java.util.Comparator;

import com.android.guyvo.Cortex;

import static com.android.guyvo.MainActivity.listCortexes;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 18, 2010
 * Time: 1:40:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Setup extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Comparator<Cortex> sensorCompare = new CortexSensorComparator();
        Collections.sort(listCortexes,sensorCompare);

    }
}
