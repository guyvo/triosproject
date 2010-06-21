package com.android.guyvo;

import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 21, 2010
 * Time: 8:53:11 PM
 * To change this template use File | Settings | File Templates.
 */

public class Cortex {

    private String name;

    private String sensor;
    private String dimmer;
    private String toggle;
    private String watchdog;
    private String hours;
    private String masks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getDimmer() {
        return dimmer;
    }

    public void setDimmer(String dimmer) {
        this.dimmer = dimmer;
    }

    public String getToggle() {
        return toggle;
    }

    public void setToggle(String toggle) {
        this.toggle = toggle;
    }

    public String getWatchdog() {
        return watchdog;
    }

    public void setWatchdog(String watchdog) {
        this.watchdog = watchdog;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMasks() {
        return masks;
    }

    public void setMasks(String masks) {
        this.masks = masks;
    }

    public Cortex(String name) {
        this.name = name;
    }

}
