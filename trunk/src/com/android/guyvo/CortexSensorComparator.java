package com.android.guyvo;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 21, 2010
 * Time: 10:48:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CortexSensorComparator implements Comparator<Cortex>{
    public int compare(Cortex cortex, Cortex cortex1) {
        return ( Integer.valueOf(cortex.getSensor()).compareTo(Integer.valueOf(cortex1.getSensor())));

    }
}
