package com.android.guyvo;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 29, 2010
 * Time: 9:53:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class LightValueComparator implements Comparator<Light> {
    public int compare(Light light, Light light1) {
         return ( Integer.valueOf(light.getValue()).compareTo(Integer.valueOf(light1.getValue())));
    }
}
