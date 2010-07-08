package com.android.guyvo;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jun 28, 2010
 * Time: 7:10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosModel implements TriosLightChangeListener,TriosCortexChangeListener{
    private List<Light> listLights;
    private List<Cortex> listCortexes;
    private Comparator<Cortex> sensorCompare = new CortexSensorComparator();
    private Comparator<Light> valueComparator = new LightValueComparator();

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

    public void registerChangeEvents ( Object o ){
       if ( (o != null) && (o instanceof TriosXmlFeed)){
           TriosXmlFeed xmlFeed = (TriosXmlFeed) o;
           xmlFeed.addTriosCortesChangeListener(this);
           xmlFeed.addTriosLightChangeListener(this);
       }
    }

    public void sortOnLightValue (){
        Collections.sort(listLights,valueComparator);
    }

    public void sortOnCortexSensor(){
        Collections.sort(listCortexes,sensorCompare);
    }

    public void setListCortexes(List<Cortex> listCortexes) {
        this.listCortexes = listCortexes;
    }

        public void onLightChange(LightChangeEvent e) {
        boolean found = false;
        Iterator it = listLights.listIterator();

        while (it.hasNext()){
            Light light = (Light) it.next();
            if (
                 (light.getCortexId().compareTo(e.getLight().getCortexId()) == 0) &&
                 (light.getLightId().compareTo(e.getLight().getLightId()) == 0)
               ){
                listLights.remove(light);
                listLights.add(e.getLight());
                found = true;
                break;
            }
        }

        if (! found){
             listLights.add(e.getLight());
        }
     }

    public void onCortexChange(CortexChangeEvent e) {
        boolean found = false;
        Iterator it = listCortexes.iterator();

        while (it.hasNext()){
            Cortex cortex = (Cortex) it.next();
            if ( cortex.getName().compareTo(e.getCortex().getName()) == 0 ){
                listCortexes.remove(cortex);
                listCortexes.add(e.getCortex());
                found = true;
                break;
            }
        }

        if (! found){
            listCortexes.add(e.getCortex());
        }
    }
}
