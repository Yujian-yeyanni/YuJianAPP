package com.xiaoyu.picturelistdemo;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BaseActivity extends FragmentActivity {
	protected FragmentTransaction transaction = null;
	protected FragmentManager fragmentManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fragmentManager = getSupportFragmentManager();
	}

	public void skipToFragmentByContentId(Fragment fragment, int contentId,
			boolean addToStack) {
		transaction = fragmentManager.beginTransaction();

		transaction.replace(contentId, fragment, fragment.getClass()
				.getSimpleName());
		if (addToStack) {
			transaction.addToBackStack(fragment.getClass().getSimpleName());
		}
		transaction.commitAllowingStateLoss();
	}
}
