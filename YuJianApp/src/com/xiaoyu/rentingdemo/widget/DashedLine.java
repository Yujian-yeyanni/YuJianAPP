package com.xiaoyu.rentingdemo.widget;


import com.xiaoyu.rentingdemo.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义虚线
 * 
 * @author shwan
 * 
 */
public class DashedLine extends View {
	private static final String TAG = DashedLine.class.getName();
	private float startX;
	private float startY;
	private float endX;
	private float endY;
	private Rect mRect;

	public DashedLine(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(R.color.color_dashedline);
		Path path = new Path();
		path.moveTo(0, 10);
		path.lineTo(1090, 10);
		PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);
		paint.setPathEffect(effects);
		canvas.drawPath(path, paint);
	}

}
