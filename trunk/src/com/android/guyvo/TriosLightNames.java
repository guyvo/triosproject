package com.android.guyvo;

import android.util.Log;

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

        lights.put("OUT1","toilet beneden");
        lights.put("OUT2","berging");
        lights.put("OUT3","vide draad brug");
        lights.put("OUT4","garage");
        lights.put("OUT5","vrij");
        lights.put("OUT6","living voor");

        names.put("Cortex1",lights);

        HashMap<String,String> lights1 = new HashMap<String,String>();

        lights1.put("OUT1","living midden");
        lights1.put("OUT2","slaapkamer beneden");
        lights1.put("OUT3","slaapkamer 1");
        lights1.put("OUT4","slaapkamer 4");
        lights1.put("OUT5","wand trap");
        lights1.put("OUT6","hal passage");

        names.put("Cortex2",lights1);

        HashMap<String,String> lights2 = new HashMap<String,String>();

        lights2.put("OUT1","wand living");
        lights2.put("OUT2","living achter");
        lights2.put("OUT3","keuken tafel");
        lights2.put("OUT4","keuken werkblad");
        lights2.put("OUT5","vrij");
        lights2.put("OUT6","terras buitendeur");

        names.put("Cortex3",lights2);

        HashMap<String,String> lights3 = new HashMap<String,String>();

        lights3.put("OUT1","badkamer beneden");
        lights3.put("OUT2","zolder");
        lights3.put("OUT3","badkamer boven");
        lights3.put("OUT4","slaapkamer 2");
        lights3.put("OUT5","slaapkamer 3");
        lights3.put("OUT6","vrij");

        names.put("Cortex4",lights3);
    }

    public static String getName(String key1, String key2){
        String name;
        name = names.get(key1).get(key2);
        Log.d("names",key1+" "+key2+ " "+name);
        return name;
    }

    public static Map<String, HashMap<String,String>> names = new HashMap<String,HashMap<String,String>>();

}


