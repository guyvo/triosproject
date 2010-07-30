package com.android.guyvo;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 17, 2010
 * Time: 10:52:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CortexNameComparator implements Comparator<Cortex> {
    public int compare(Cortex cortex, Cortex cortex1) {
        return (cortex.getName()).compareTo(cortex1.getName());
    }
}
