package com.android.guyvo;

import java.util.EventListener;

public interface XmlParseListener extends EventListener {
    public abstract void onXmlParseChange(XmlParseChangeEvent e);
}
