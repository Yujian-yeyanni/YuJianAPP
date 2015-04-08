package com.xiaoyu.rentingdemo.adapter;

import java.util.List;

import com.baidu.mapapi.map.InfoWindow;
import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.data.bean.PersonalInfoBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonalAdapter extends BaseAdapter {

	private Context context;
	private List<PersonalInfoBean> personalInfoBeans;

	public PersonalAdapter(Context context, List<PersonalInfoBean> infoBeans) {
		this.context = context;
		this.personalInfoBeans = infoBeans;
	}

	@Override
	public int getCount() {
		if (personalInfoBeans != null) {
			return personalInfoBeans.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return personalInfoBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View conventView, ViewGroup viewGroup) {
		ViewHolder holder = null;
		if (conventView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(context);
			conventView = inflater.inflate(R.layout.item_personal_have_login,
					null);
			holder.textPersonalInfo = (TextView) conventView
					.findViewById(R.id.tv_personal_info);
			conventView.setTag(holder);
		} else {
			holder = (ViewHolder) conventView.getTag();
		}
		// set data to personal page
		if (personalInfoBeans == null) {
			return null;
		}
		PersonalInfoBean infoBean = personalInfoBeans.get(position);
		
		holder.textPersonalInfo.setText(infoBean.getTypeString());
		return conventView;
	}

	class ViewHolder {
		TextView textPersonalInfo;
	}

}
