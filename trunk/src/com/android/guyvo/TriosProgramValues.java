package com.android.guyvo;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Aug 6, 2010
 * Time: 2:04:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriosProgramValues implements SliderView.SliderViewListner{

    ArrayList mValues;

    public TriosProgramValues(int size) {
        mValues = new ArrayList(size);
    }

    public ArrayList getmValues() {
        return mValues;
    }

    public void setmValues(ArrayList mValues) {
        this.mValues = mValues;
    }

    public void setValue (int value , int position){
        mValues.set(position,value);
    }

    public Object getValue (int position){
        return  mValues.get(position);
    }

    public void addValue (int value,int position){
        mValues.add(position);
    }

    public int getSize (){
        return mValues.size();
    }

    public void onSliderValueChanged(int value, int position) {
        setValue(value,position);
    }
}
