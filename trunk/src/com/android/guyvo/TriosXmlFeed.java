package com.android.guyvo;

import java.io.IOException;
import java.io.InputStream;
import java.util.EventObject;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;

public class TriosXmlFeed implements XmlParseListener {

    private final String CORTEXTAGNAME = "Cortex";
    private final String LIGHTTAGNAME = "Light";
    private String url;

    private XmlParser xml = null;

    private Vector<TriosLightChangeListener> triosLightChangeListeners = new Vector<TriosLightChangeListener>();
    private Vector<TriosCortexChangeListener> triosCortexChangeListeners = new Vector<TriosCortexChangeListener>();

    private Light light;
    private Cortex cortex;

    public static String toText(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    public void postXml(String xml) throws IOException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(xml);
        stringEntity.setContentType("text/xml");
        httpPost.setEntity(stringEntity);
        httpPost.addHeader("cortex", "4");
        HttpResponse response = httpclient.execute(httpPost);
    }

    public void getXml() throws Exception {
        InputStream content = null;


        HttpGet httpGet = new HttpGet(url);
       
        httpGet.addHeader("cortex", "4");
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpGet);
        content = response.getEntity().getContent();

        xml = new XmlParser(content);
        xml.addXmlParseListener(this);
        xml.Parse();
    }

    public TriosXmlFeed(String url) throws IOException {
        this.url = url;
    }

    public void onXmlParseChange(XmlParseChangeEvent e) {
        XmlPullParser data = e.getObject();

        switch (e.getEvent()) {
            case XmlPullParser.START_DOCUMENT:
                break;
            case XmlPullParser.START_TAG:
                if (data.getName().equals(CORTEXTAGNAME)) {
                    cortex = new Cortex(data.getAttributeValue(0));
                    cortex.setSensor(data.getAttributeValue(1));
                    cortex.setWatchdog(data.getAttributeValue(2));
                    cortex.setToggle(data.getAttributeValue(3));
                    cortex.setDimmer(data.getAttributeValue(4));
                    cortex.setHours(data.getAttributeValue(5));
                    cortex.setMasks(data.getAttributeValue(6));
                    notifyCortexChange(cortex);
                }else if (data.getName().equals(LIGHTTAGNAME)) {
                    light = new Light(cortex.getName(), data.getAttributeValue(0));
                    light.setValue(data.getAttributeValue(1));
                    light.setMin(data.getAttributeValue(2));
                    light.setMax(data.getAttributeValue(3));
                    light.setStep(data.getAttributeValue(4));
                    light.setPinout(data.getAttributeValue(5));
                    light.setPinin(data.getAttributeValue(6));
                    notifyLightChange(light);
                }
                break;
            case XmlPullParser.TEXT:
                break;
            case XmlPullParser.END_TAG:

                break;
            case XmlPullParser.END_DOCUMENT:
                break;
        }
    }

    public synchronized void addTriosLightChangeListener(TriosLightChangeListener triosLightChangeListener) {
        triosLightChangeListeners.addElement(triosLightChangeListener);
    }

    public synchronized void removeTriosLightChangeListener(TriosLightChangeListener triosLightChangeListener) {
        triosLightChangeListeners.removeElement(triosLightChangeListener);
    }

    public synchronized void addTriosCortesChangeListener(TriosCortexChangeListener triosCortexChangeListener) {
        triosCortexChangeListeners.addElement(triosCortexChangeListener);
    }

    public synchronized void removeCortexLightChangeListener(TriosCortexChangeListener triosCortexChangeListener) {
        triosLightChangeListeners.removeElement(triosCortexChangeListener);
    }

    protected void notifyLightChange(Light light) {
        LightChangeEvent e = new LightChangeEvent(this, light);

        for (int i = 0; i < triosLightChangeListeners.size(); i++) {
            triosLightChangeListeners.elementAt(i).onLightChange(e);
        }
    }

    protected void notifyCortexChange(Cortex cortex) {
        CortexChangeEvent e = new CortexChangeEvent(this, cortex);

        for (int i = 0; i < triosCortexChangeListeners.size(); i++) {
            triosCortexChangeListeners.elementAt(i).onCortexChange(e);
        }
    }

}
