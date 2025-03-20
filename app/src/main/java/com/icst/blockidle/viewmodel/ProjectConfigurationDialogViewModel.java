/*
 *  This file is part of Block IDLE.
 *
 *  Block IDLE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Block IDLE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with Block IDLE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.icst.blockidle.viewmodel;

import com.google.android.material.textfield.TextInputEditText;
import com.icst.blockidle.bean.ProjectBean;
import com.icst.blockidle.listener.ProjectConfigurationDialogListener;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class ProjectConfigurationDialogViewModel extends BaseObservable {

	private ProjectBean projectBean;
	private ProjectConfigurationDialogListener listener;
	private String projectName;
	private String versionName;

	public String getConfigMode() {
		if (projectBean == null) {
			return "Create";
		} else {
			return "Update";
		}
	}

	public void onCancel() {
		listener.onCancel();
	}

	public void onCreate() {
		ProjectBean projectBean = new ProjectBean();
		projectBean.setProjectVersionName(versionName == null ? "null" : versionName);
		projectBean.setProjectName(projectName == null ? "null" : projectName);
		listener.onCreateNewProject(projectBean);
	}

	public ProjectBean getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}

	public ProjectConfigurationDialogListener getListener() {
		return this.listener;
	}

	public void setListener(ProjectConfigurationDialogListener listener) {
		this.listener = listener;
	}

	@BindingAdapter("text")
	public static void setText(TextInputEditText view, String newValue) {
		if (!newValue.equals(view.getText().toString())) {
			view.setText(newValue);
		}
	}

	@InverseBindingAdapter(attribute = "text", event = "android:textAttrChanged")
	public static String getText(TextInputEditText view) {
		return view.getText().toString();
	}

	public String getProjectName() {
		if (projectBean != null) {
			return projectBean.getProjectName() == null ? "" : projectBean.getProjectName();
		}

		return "";
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionName() {
		if (projectBean != null) {
			return projectBean.getProjectVersionName() == null
					? ""
					: projectBean.getProjectVersionName();
		}

		return "";
	}

}
