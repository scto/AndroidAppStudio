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

import com.google.android.material.textfield.TextInputLayout;
import com.icst.blockidle.bean.ProjectBean;
import com.icst.blockidle.listener.ProjectConfigurationDialogListener;
import com.icst.blockidle.util.ProjectBeanValidator;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProjectConfigurationDialogViewModel extends ViewModel {

	private ProjectBean projectBean;
	private ProjectConfigurationDialogListener listener;
	private final MutableLiveData<String> projectName = new MutableLiveData<>("");
	private final MutableLiveData<Boolean> isValidProjectName = new MutableLiveData<>(true);
	private final MutableLiveData<String> packageName = new MutableLiveData<>("");
	private final MutableLiveData<Boolean> isValidPackageName = new MutableLiveData<>(true);

	public ProjectConfigurationDialogViewModel() {
		projectName.observeForever((p) -> {
			validateProjectName(projectName.getValue());
		});

		packageName.observeForever((p) -> {
			validatePackageName(packageName.getValue());
		});
	}

	@BindingAdapter("app:errorEnabled")
	public static void setErrorEnabled(TextInputLayout view, boolean enableError) {
		view.setErrorEnabled(enableError);
	}

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
		projectBean.setProjectPackageName(packageName.getValue() == null ? "null" : packageName.getValue());
		projectBean.setProjectName(projectName.getValue() == null ? "null" : projectName.getValue());
		listener.onCreateNewProject(projectBean);
	}

	public ProjectBean getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
		if (projectBean != null) {
			projectName.postValue(projectBean.getProjectName());
			packageName.postValue(projectBean.getProjectPackageName());
		}
	}

	public ProjectConfigurationDialogListener getListener() {
		return this.listener;
	}

	public void setListener(ProjectConfigurationDialogListener listener) {
		this.listener = listener;
	}

	public MutableLiveData<Boolean> isValidProjectName() {
		return isValidProjectName;
	}

	public MutableLiveData<Boolean> isValidPackageName() {
		return isValidPackageName;
	}

	private void validateProjectName(String name) {
		boolean isValid = ProjectBeanValidator.isValidProjectName(name);
		isValidProjectName.postValue(isValid);
	}

	private void validatePackageName(String name) {
		boolean isValid = ProjectBeanValidator.isValidPackageName(name);
		isValidPackageName.postValue(isValid);
	}

	public MutableLiveData<String> getProjectName() {
		return this.projectName;
	}

	public MutableLiveData<String> getPackageName() {
		return this.packageName;
	}
}
