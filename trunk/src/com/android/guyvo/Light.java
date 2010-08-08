package com.android.guyvo;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 21, 2010
 * Time: 8:52:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Light {

    private final String OFF = "10000";
    private final String ON  = "0";
    private final int TIME = 10000;

    private String name;
    private String cortexId;
    private String lightId;

    private String value;
    private String min;
    private String max;
    private String pinin;
    private String pinout;
    private String step;




    public String toXml(){
        // ensure we have an empty builder every time we want to convert
        StringBuilder xml = new  StringBuilder();

        xml.append("<Light NAME=\"");
        xml.append(lightId + "\"");
        xml.append(" VALUE=\"");
        xml.append(value + "\"");
        xml.append(" MIN=\"");
        xml.append(min + "\"");
        xml.append(" MAX=\"");
        xml.append(max + "\"");
        xml.append(" DELTA=\"");
        xml.append(step + "\"");
        xml.append(" PININ=\"");
        xml.append(pinin + "\"");
        xml.append(" PINOUT=\"");
        xml.append(pinout + "\"");
        xml.append("/>\n");

        return xml.toString();
    }

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

    public int getViewValue() {
        return (10000 - Integer.parseInt(value))/100;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(int value) {
        int v;
        v = TIME - (TIME * value) / 100;

        if ( v > Integer.parseInt(min)){
            setValue(OFF);
        }
        else if ( v < Integer.parseInt(max)){
            setValue(ON);
        }
        else
            setValue(String.valueOf(v));
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

    public Light(String cortexId, String lightId) {
        this.cortexId = cortexId;
        this.lightId = lightId;
    }
}
