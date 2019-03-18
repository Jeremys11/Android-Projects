package com.example.jerem.amp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

public class KnobView extends View {

    /***************************/
    /***************************/
    /***************************/
    // This is the setup to allow other files to implement
    public interface OnAngleChangedListener {
        void onAngleChanged(float theta);
    }

    private float m_theta = 0.0f; // Changes position of nib
    private RectF m_knobRect = new RectF(); // Creating big oval
    private OnAngleChangedListener m_angleChangedListener = null; /***************************/

    public void setTheta(float theta){
        m_theta = theta;
        invalidate(); //invalidates pixels on screen to redraw
    }

    public float getTheta(){
        return m_theta;
    }

    public KnobView(Context context) {
        super(context);
    }

    /***************************/
    /***************************/
    /***************************/
    // Function to be called to assign OnAngleChangedListener
    public void setOnAngleChangedListener(OnAngleChangedListener listener){
        m_angleChangedListener = listener;
    }

    /***************************/
    /***************************/
    /***************************/
    // Creates event that is passed to the listener
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF touchPoint = new PointF();
        touchPoint.x = event.getX() - m_knobRect.centerX();
        touchPoint.y = event.getY() - m_knobRect.centerY();

        float theta = (float)Math.atan2(touchPoint.y,touchPoint.x);
        setTheta(theta);
        m_angleChangedListener.onAngleChanged(theta); /***************************/

        return true; // Continue to create events as touchpoint moves
    }

    // Overriding OnDraw to make custom image
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Floats
        m_knobRect.left = getPaddingLeft();
        m_knobRect.top = getPaddingTop();
        m_knobRect.right = getWidth() - getPaddingRight(); // getWidth from View
        m_knobRect.bottom = getWidth(); // To make it a circle, width = height
        // m_knobRect.bottom = getHeight(); getHeight from Height

        Paint knobPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        knobPaint.setColor(Color.GREEN);

        float offset = (getHeight() - m_knobRect.height()) * 0.5f;
        m_knobRect.top += offset;
        m_knobRect.bottom += offset;

        //RectF, Paint - big oval
        canvas.drawOval(m_knobRect, knobPaint);


        // Settings for small oval
        float knobRadius = m_knobRect.width() * 0.35f;

        // PointF to get edges of oval
        // From center of big oval to some point in polar coordinates
        PointF nibCenter = new PointF();
        nibCenter.x = m_knobRect.centerX() + knobRadius * (float)Math.cos((double)m_theta);
        nibCenter.y = m_knobRect.centerY() + knobRadius * (float)Math.sin((double)m_theta);

        float nibRadius = knobRadius * 0.2f;

        // Making small oval
        Paint nibPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        nibPaint.setColor(Color.YELLOW);

        RectF nibRect = new RectF();

        nibRect.left = nibCenter.x - nibRadius;
        nibRect.top = nibCenter.y - nibRadius;
        nibRect.right = nibCenter.x + nibRadius;
        nibRect.bottom = nibCenter.y + nibRadius;

        canvas.drawOval(nibRect,nibPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpec = MeasureSpec.getMode(heightMeasureSpec);

        int width = 400;
        int height = 400;

        setMeasuredDimension(
                resolveSize(width,widthMeasureSpec),
                resolveSize(height,heightMeasureSpec));
    }
}
