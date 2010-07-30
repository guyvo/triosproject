package com.android.guyvo;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
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
    private Paint paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
    private LinearGradient linearGradient;
    private boolean drawingBusy = true;
    private float initX, initY = 50;
    private String logText = "";
    private boolean isEnabled = true;

    public SliderView(Context context) {
        super(context);
        init();
    }

    public SliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPadding(5, 5, 5, 10);
        init();
    }

    public SliderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setStrokeWidth(1f);
        paintStroke.setColor(Color.BLUE);
        setFocusable(true);
    }

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setValue(int value) {
        initX = ((10000 - value) / 10000f) * getMeasuredWidth();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect rect = new Rect(0, 0, (int) initX, (int) initY);
        Rect stroke = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight() - getPaddingBottom());

        //if (drawingBusy) {
        canvas.drawRect(rect, paintRect);
        canvas.drawRect(stroke, paintStroke);
        canvas.drawText(String.valueOf(initX), ((getMeasuredWidth() / 2)), getMeasuredHeight() / 2, paintStroke);
        //}
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        initY = getMeasuredHeight() - getPaddingBottom();
        linearGradient = new LinearGradient(0f, 0f, getMeasuredWidth(), getMeasuredHeight(), Color.BLACK, Color.YELLOW, Shader.TileMode.CLAMP);
        paintRect.setShader(linearGradient);
        paintStroke.setTextSize(getMeasuredHeight() / 4);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled) {

            int action = event.getAction();
            if (action == MotionEvent.ACTION_MOVE) {
                initX = event.getX();
            } else if (action == MotionEvent.ACTION_DOWN) {
                initX = event.getX();
                drawingBusy = true;
            } else if (action == MotionEvent.ACTION_UP) {
                drawingBusy = false;
            }

            //if (drawingBusy)
            invalidate();

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
        logText = sb.toString();
        //Log.d(TAG, sb.toString());

    }
}
