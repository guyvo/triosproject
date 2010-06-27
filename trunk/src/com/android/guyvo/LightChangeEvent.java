package com.android.guyvo;

import java.util.EventObject;

public class LightChangeEvent extends EventObject {

	private static final long serialVersionUID = 1L;
    private Light light;

    public Light getLight() {
        return light;
    }

	public LightChangeEvent(Object source , Light light) {
		super(source);
        this.light = light;
	}
}
