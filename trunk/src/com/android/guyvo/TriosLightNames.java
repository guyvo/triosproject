package com.android.guyvo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 11, 2010
 * Time: 9:30:11 PM
 * To change this template use File | Settings | File Templates.
 */

public class  TriosLightNames {
    public static void setNames(){
        HashMap<String,String> lights = new HashMap<String,String>();

        lights.put("OUT1","LIV1");
        lights.put("OUT2","LIV1");
        lights.put("OUT3","LIV1");
        lights.put("OUT4","LIV1");
        lights.put("OUT5","LIV1");
        lights.put("OUT6","LIV1");

        names.put("Cortex1",lights);

        lights.clear();
        lights.put("OUT1","LIV1");
        lights.put("OUT2","LIV1");
        lights.put("OUT3","LIV1");
        lights.put("OUT4","LIV1");
        lights.put("OUT5","LIV1");
        lights.put("OUT6","LIV1");

        names.put("Cortex2",lights);

        lights.clear();
        lights.put("OUT1","LIV1");
        lights.put("OUT2","LIV1");
        lights.put("OUT3","LIV1");
        lights.put("OUT4","LIV1");
        lights.put("OUT5","LIV1");
        lights.put("OUT6","LIV1");

        names.put("Cortex3",lights);

        lights.clear();
        lights.put("OUT1","LIV1");
        lights.put("OUT2","LIV1");
        lights.put("OUT3","LIV1");
        lights.put("OUT4","LIV1");
        lights.put("OUT5","LIV1");
        lights.put("OUT6","LIV1");

        names.put("Cortex4",lights);
    }

    public static String getName(String key1, String key2){
        return names.get(key1).get(key2);
    }

    public static Map<String, HashMap<String,String>> names = new HashMap<String,HashMap<String,String>>();

}


