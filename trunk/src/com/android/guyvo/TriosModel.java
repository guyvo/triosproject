package com.android.guyvo;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 28, 2010
 * Time: 7:10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosModel implements TriosLightChangeListener, TriosCortexChangeListener , SliderView.SliderViewListner{

    private List<Light> listLights;
    private List<Cortex> listCortexes;
    private Comparator<Cortex> sensorCompare = new CortexSensorComparator();
    private Comparator<Cortex> nameCompare = new CortexNameComparator();
    private Comparator<Light> valueComparator = new LightValueComparator();
    private Comparator<Light> nameComparator = new LightNameComparator();

    public TriosModel() {
        listLights = new ArrayList<Light>();
        listCortexes = new ArrayList<Cortex>();
    }

    public List<Light> getListLights() {
        return listLights;
    }

    public void setListLights(List<Light> listLights) {
        this.listLights = listLights;
    }

    public List<Cortex> getListCortexes() {
        return listCortexes;
    }

    public void registerChangeEvents(Object o) {
        if ((o != null) && (o instanceof TriosXmlFeed)) {
            TriosXmlFeed xmlFeed = (TriosXmlFeed) o;
            xmlFeed.addTriosCortesChangeListener(this);
            xmlFeed.addTriosLightChangeListener(this);
        }
    }

    public void sortOnLightValue() {
        Collections.sort(listLights, valueComparator);
    }

    public void sortOnLightName() {
        Collections.sort(listLights, nameComparator);
    }

    public void sortOnCortexSensor() {
        Collections.sort(listCortexes, sensorCompare);
    }

    public void sortOnCortexName() {
        Collections.sort(listCortexes, nameCompare);
    }

    public void setListCortexes(List<Cortex> listCortexes) {
        this.listCortexes = listCortexes;
    }

    public void onLightChange(LightChangeEvent e) {
        boolean found = false;
        Iterator it = listLights.listIterator();

        while (it.hasNext()) {
            Light light = (Light) it.next();
            if (
                    (light.getCortexId().compareTo(e.getLight().getCortexId()) == 0) &&
                            (light.getLightId().compareTo(e.getLight().getLightId()) == 0)
                    ) {
                listLights.remove(light);
                listLights.add(e.getLight());
                found = true;
                break;
            }
        }

        if (!found) {
            listLights.add(e.getLight());
        }
    }

    public void onCortexChange(CortexChangeEvent e) {
        boolean found = false;
        Iterator it = listCortexes.iterator();

        while (it.hasNext()) {
            Cortex cortex = (Cortex) it.next();
            if (cortex.getName().compareTo(e.getCortex().getName()) == 0) {
                listCortexes.remove(cortex);
                listCortexes.add(e.getCortex());
                found = true;
                break;
            }
        }

        if (!found) {
            listCortexes.add(e.getCortex());
        }
    }

    public void onSliderValueChanged(int value, int position) {
        getListLights().get(position).setValue(value);
    }

    public String toXml (){

         // ensure we have an empty builder every time we want to convert
        StringBuilder xml = new StringBuilder();

        Iterator cortexIterator = listCortexes.iterator();
        Iterator lightIterator = listLights.listIterator();

        sortOnCortexName();
        sortOnLightName();

        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<DATA>\n");
        xml.append("<Cortexes>\n");

        while (cortexIterator.hasNext()){
            Cortex cortex = (Cortex)cortexIterator.next();
            xml.append(cortex.toXml());
            xml.append("<Lights> \n");
            for (int i=0 ; i < listLights.size() ; i++){
                if ( cortex.getName().compareTo(listLights.get(i).getCortexId()) == 0 ){
                    xml.append(listLights.get(i).toXml());
                }
            }
            xml.append("</Lights> \n");
            xml.append("</Cortex> \n");
        }

        xml.append("</Cortexes>\n");
        xml.append("</DATA>\n");

        return xml.toString();
    }

}
