package com.android.guyvo;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.EventListener;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 7, 2010
 * Time: 8:02:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SliderView extends View {

    private Paint mPaintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
    private LinearGradient mLinearGradient;
    private boolean mDrawingBusy = true;
    private float mTheX;
    private float mTheY;
    private int mValue;
    private int mPosition;
    private boolean isEnabled = true;

    private Vector<SliderViewListner> sliderViewListners = new Vector<SliderViewListner>();

    public interface SliderViewListner extends EventListener {
        public abstract void onSliderValueChanged(int value, int position);
    }

    public void addSliderViewListner(SliderViewListner sliderViewListner) {
        sliderViewListners.addElement(sliderViewListner);
    }

    public void removeliderViewListner(SliderViewListner sliderViewListner) {
        sliderViewListners.remove(sliderViewListner);
    }

    public SliderView(Context context) {
        super(context);
        setPadding(5, 5, 5, 5);
        init();
    }

    public SliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPadding(5, 5, 5, 5);
        init();
    }

    public SliderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPadding(5, 5, 5, 5);
        init();
    }

    private void init() {
        mPaintStroke.setStyle(Paint.Style.STROKE);
        mPaintStroke.setStrokeWidth(1f);
        mPaintStroke.setColor(0xFFAA3040);
    }

    private void convertToView(int value) {
        mTheX = (value / 100f) * getWidth();
    }

    private int convertToValue() {
        return (int) ((mTheX / getWidth()) * 100);
    }

    public void setValue(int x) {
        mValue = x;
    }

    public void setValue(int x, int position) {
        mValue = x;
        mPosition = position;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getValue() {
        return convertToValue();
    }

    public void notifyValueChanged() {
        for (int i = 0; i < sliderViewListners.size(); i++) {
            sliderViewListners.get(i).onSliderValueChanged(getValue(), mPosition);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        convertToView(mValue);
        Rect rect = new Rect(0, 0, (int) mTheX, (int) mTheY);
        Rect stroke = new Rect(0, 0, getWidth(), getHeight() - getPaddingBottom());
        canvas.drawRect(rect, mPaintRect);
        canvas.drawRect(stroke, mPaintStroke);
        canvas.drawText(String.valueOf(mValue), ((getWidth() / 2)), getHeight() / 2, mPaintStroke);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        mTheY = getMeasuredHeight() - getPaddingBottom();
        mLinearGradient = new LinearGradient(0f, 0f, getMeasuredWidth(), getMeasuredHeight(), Color.BLACK, Color.YELLOW, Shader.TileMode.CLAMP);
        mPaintRect.setShader(mLinearGradient);
        mPaintStroke.setTextSize(getMeasuredHeight() / 4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_MOVE) {
                mTheX = (int) event.getX();
            } else if (action == MotionEvent.ACTION_DOWN) {
                mTheX = (int) event.getX();
            } else if (action == MotionEvent.ACTION_UP) {
            }

            mValue = convertToValue();

            invalidate();

            notifyValueChanged();

            return true;
        }

        return false;
    }

    private void dumpEvent(MotionEvent event) {
        String names[] = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }
        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())
                sb.append(";");
            sb.append(" press=" + Float.toString(event.getPressure(i)));
        }
        sb.append("]");
        Log.d("dumpEvent", sb.toString());
    }
}
