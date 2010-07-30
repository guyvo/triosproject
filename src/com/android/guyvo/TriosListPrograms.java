package com.android.guyvo;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 30, 2010
 * Time: 8:38:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosListPrograms extends Activity {
    Properties properties = new Properties();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        properties.setProperty("1","guy");
        properties.setProperty("2","guy");
        properties.setProperty("3","guy");
        properties.setProperty("4","guy");

        try {
            OutputStream outputStream = new FileOutputStream("/sdcard/trios/program1.xml");
            properties.storeToXML(outputStream,"comment");
            outputStream.flush();
            outputStream.close();
            OutputStream outputStream1 = new FileOutputStream("/sdcard/trios/program2.xml");
            properties.storeToXML(outputStream1,"comment");
            outputStream1.flush();
            outputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
