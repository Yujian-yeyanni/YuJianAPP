package com.xiaoyu.rentingdemo.widget;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyu.rentingdemo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * house detail quality label view
 * 
 * @author shwan
 * 
 */
public class QualityLabelView extends View {

	private static final String TAG = QualityLabelView.class.getName();
	private Context context;
	private LinearLayout layoutParent;

	// TODO ADD THE IMAGEID TO ARRAY
	private int[] labelImageId = { R.drawable.ic_renter_approve,
			R.drawable.ic_quality_zone, R.drawable.ic_free_wifi,
			R.drawable.ic_safety_protection, R.drawable.ic_deep_clear,
			R.drawable.ic_timely_fix };
	// label string id array
	private int[] labelStrId = { R.string.str_renter_approve,
			R.string.str_quality_zone, R.string.str_free_wifi,
			R.string.str_safety_protection, R.string.str_deep_clear,
			R.string.str_timely_fix };

	// CONSTRUCT METHOD
	public QualityLabelView(Context context) {
		super(context);
		this.context = context;
	}

	public QualityLabelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public QualityLabelView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
	}

	/**
	 * add label to layout
	 * 
	 * @param linearLayout
	 * @param labelCount
	 */
	public void setLabelView(LinearLayout linearLayout, int labelCount) {
		layoutParent = linearLayout;
		int size = labelCount;
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		// params.setMargins(20, 0, 40, 0);
		List<View> qualityLabelViews = new ArrayList<View>();
		int totalView = 0;
		for (int i = 0; i <= size; i++) {
			LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(
					R.layout.item_house_detail_quality_label, layoutParent,
					false);
			ImageView imageLabel = (ImageView) view
					.findViewById(R.id.iv_house_quailty_label);
			TextView textLabel = (TextView) view
					.findViewById(R.id.tv_house_quality_label);
			imageLabel.setBackgroundResource(labelImageId[i]);
			textLabel.setText(labelStrId[i]);
			qualityLabelViews.add(view);
			totalView++;
			// itemParams.setMargins(20, 20, 0, 0);
			if (totalView >= 4) {
				LinearLayout horizLL = new LinearLayout(
						context.getApplicationContext());
				horizLL.setOrientation(LinearLayout.HORIZONTAL);
				horizLL.setLayoutParams(params);
				for (View labelView : qualityLabelViews) {
					horizLL.addView(labelView);
				}
				layoutParent.addView(horizLL);
				qualityLabelViews.clear();
				totalView = 0;
			}
		}
		// 最后一行添加一下
		if (!qualityLabelViews.isEmpty()) {
			LinearLayout horizLL = new LinearLayout(
					context.getApplicationContext());
			horizLL.setOrientation(LinearLayout.HORIZONTAL);
			horizLL.setLayoutParams(params);

			for (View labelView : qualityLabelViews) {
				horizLL.addView(labelView);
			}
			layoutParent.addView(horizLL);
			qualityLabelViews.clear();
			totalView = 0;
		}

	}
}
