package com.android.guyvo;

import java.util.EventListener;

public interface TriosLightChangeListener extends EventListener {
	public abstract void onLightChange (LightChangeEvent e);
}
