package com.android.guyvo;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 21, 2010
 * Time: 8:52:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Light {
    private String name;
    private String cortexId;
    private String lightId;

    private String value;
    private String min;
    private String max;
    private String pinin;
    private String pinout;
    private String step;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCortexId() {
        return cortexId;
    }

    public void setCortexId(String cortexId) {
        this.cortexId = cortexId;
    }

    public String getLightId() {
        return lightId;
    }

    public void setLightId(String lightId) {
        this.lightId = lightId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getPinin() {
        return pinin;
    }

    public void setPinin(String pinin) {
        this.pinin = pinin;
    }

    public String getPinout() {
        return pinout;
    }

    public void setPinout(String pinout) {
        this.pinout = pinout;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Light(String name, String cortexId, String lightId) {
        this.name = name;
        this.cortexId = cortexId;
        this.lightId = lightId;
    }
}
