package com.android.guyvo;

import java.util.EventObject;

public class CortexChangeEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    private Cortex cortex;

    public Cortex getCortex() {
        return cortex;
    }

    public CortexChangeEvent(Object source, Cortex cortex) {
        super(source);
        this.cortex = cortex;
    }
}
