package com.android.guyvo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: guy
 * Date: Jul 7, 2010
 * Time: 8:02:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SliderView extends View {
    private Paint paintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean drawingBusy = true;
    private float initX, initY = 50;
    private String logText="";

    public SliderView(Context context) {
        super(context);
        init();
    }

    public SliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SliderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paintRect.setStyle(Paint.Style.FILL);
        paintRect.setColor(Color.BLUE );
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(8f);
        //paintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect(0, 0, (int) initX, (int) initY);

        //if (drawingBusy) {
            canvas.drawRect(rect, paintRect);
            canvas.drawText(logText, 5f, 30f, paintText);
        //}
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            initX = event.getX();
            initY = event.getY();

        } else if (action == MotionEvent.ACTION_DOWN) {
            initX = event.getX();
            initY = event.getY();
            drawingBusy = true;
        } else if (action == MotionEvent.ACTION_UP) {
            drawingBusy = false;
        }

        //if (drawingBusy)
            invalidate();

        dumpEvent(event);

        return true;
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
        logText = sb.toString();
        //Log.d(TAG, sb.toString());

    }
}
