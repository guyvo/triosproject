package com.android.guyvo;

import org.xmlpull.v1.XmlPullParser;

import java.util.EventObject;

public class XmlParseChangeEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private int event;

    public XmlParseChangeEvent(Object source, int event) {
        super(source);
        this.event = event;
    }

    public int getEvent() {
        return this.event;
    }

    public XmlPullParser getObject() {
        return (XmlPullParser) getSource();
    }
}
