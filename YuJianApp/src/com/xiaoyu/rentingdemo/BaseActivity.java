package com.xiaoyu.rentingdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BaseActivity extends FragmentActivity {
	protected FragmentTransaction transaction = null;
	protected FragmentManager fragmentManager = null;

	protected static ProgressDialog progressDialog;// 等待对话框

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragmentManager = getSupportFragmentManager();
	}

	/**
	 * skip to framgent
	 * 
	 * @param fragment
	 * @param contentId
	 * @param addToStack
	 */
	public void skipToFragmentByContentId(Fragment fragment, int contentId,
			boolean addToStack) {
		transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.animator.move_in,
				R.animator.move_out, R.animator.move_back_int,
				R.animator.move_back_out);
		transaction.replace(contentId, fragment, fragment.getClass()
				.getSimpleName());
		if (addToStack) {
			transaction.addToBackStack(fragment.getClass().getSimpleName());
		}
		transaction.commitAllowingStateLoss();
	}

	/**
	 * add to fragment
	 * 
	 * @param fragment
	 * @param contentId
	 * @param addToStack
	 */
	public void addToFragmentByContentId(Fragment fragment, int contentId,
			boolean addToStack) {
		transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.animator.move_in,
				R.animator.move_out, R.animator.move_back_int,
				R.animator.move_back_out);
		transaction.add(contentId, fragment, fragment.getClass()
				.getSimpleName());
		if (addToStack) {
			transaction.addToBackStack(fragment.getClass().getSimpleName());
		}
		transaction.commitAllowingStateLoss();
	}

	/**
	 * 显示等待对话框 等待对应网络连接任务，当等待对话框被用户手动关闭后，网络连接任务终止
	 * 
	 * @param waitMsg
	 *            等待提示信息
	 * @param netTask
	 *            等待对应的网络连接任务
	 * @param context
	 *            显示页面的context
	 */
	public void showWait(int strId) {
		dismissDialog();
		String waitMsg = "";
		waitMsg = this.getText(R.string.str_loading_message).toString();
		if (waitMsg == null || waitMsg.equals("")) {
			waitMsg = getText(R.string.str_loading_message).toString();
		}

		progressDialog = new ProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage(waitMsg);
		progressDialog.show();
	}

	/**
	 * 关闭对话框
	 */
	public void dismissDialog() {
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
				progressDialog = null;
			}
		} catch (Throwable t) {

		}
	}

	/**
	 * 返回键监听
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// TODO add back listener
	}
}
