package com.android.guyvo;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 17, 2010
 * Time: 11:03:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class LightNameComparator implements Comparator<Light> {
   public int compare(Light light, Light light1) {
        return (light.getCortexId().compareTo(light1.getCortexId()));
    }
}
