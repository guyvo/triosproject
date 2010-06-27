package com.android.guyvo;

import java.util.EventListener;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 27, 2010
 * Time: 4:06:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TriosCortexChangeListener extends EventListener{
    public abstract void onCortexChange ( CortexChangeEvent e);

}
