package com.android.guyvo;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.Vector;

public class XmlParser {
    private InputStream inputStream = null;
    private Vector<XmlParseListener> xmlListeners = new Vector<XmlParseListener>();

    XmlParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void Parse() throws Exception {
        XmlPullParser parser = Xml.newPullParser();

        parser.setInput(inputStream, null);
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            notifyXmlParseChange(parser, eventType);
            eventType = parser.next();
        }
    }

    public synchronized void addXmlParseListener(XmlParseListener xmlParseListener) {
        xmlListeners.addElement(xmlParseListener);
    }

    public synchronized void removeXmlParseListener(XmlParseListener xmlParseListener) {
        xmlListeners.removeElement(xmlParseListener);
    }

    protected void notifyXmlParseChange(Object source, int event) {
        XmlParseChangeEvent e = new XmlParseChangeEvent(source, event);

        for (int i = 0; i < xmlListeners.size(); i++) {
            xmlListeners.elementAt(i).onXmlParseChange(e);
        }
    }
}
