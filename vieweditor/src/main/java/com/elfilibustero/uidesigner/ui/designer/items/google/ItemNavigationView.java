package com.elfilibustero.uidesigner.ui.designer.items.google;

import com.elfilibustero.uidesigner.ui.designer.DesignerItem;
import com.google.android.material.navigation.NavigationView;
import com.icst.layout.editor.R;

import android.content.Context;
import android.view.MotionEvent;

public class ItemNavigationView extends NavigationView implements DesignerItem {

	public ItemNavigationView(Context context) {
		super(context);
		initialize();
	}

	private void initialize() {
		inflateMenu(R.menu.dummy_bottom_navigation_item);
		setFocusable(false);
		setClickable(false);
	}

	private String className;

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public Class<?> getClassType() {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			return NavigationView.class;
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
		return true;
	}
}
