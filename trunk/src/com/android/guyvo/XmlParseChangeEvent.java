package com.android.guyvo;

import java.util.EventObject;

import org.xmlpull.v1.XmlPullParser;

public class XmlParseChangeEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private int event;
	
	public XmlParseChangeEvent(Object source, int event) {
		super(source);
		this.event = event;
	}
	
	public int getEvent (){
		return this.event;		
	}
	
	public XmlPullParser getObject(){
		return 	(XmlPullParser) getSource();	
	}
}
