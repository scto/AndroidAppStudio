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

import com.icst.blockidle.activities.project_editor.ProjectEditorActivity;
import com.icst.blockidle.util.JavaValidators;
import com.icst.blockidle.util.ProjectBeanValidator;
import com.icst.blockidle.util.ProjectFile;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewJavaClassDialogViewModel extends ViewModel {

	private ProjectFile projectFile;
	private AlertDialog alertDialog;
	private ProjectEditorActivity projectEditorActivity;
	private final MutableLiveData<String> packageName = new MutableLiveData<>("");
	private final MutableLiveData<String> javaClassName = new MutableLiveData<>("");

	public void createJavaFile() {
		boolean isValidClassName = JavaValidators.isValidJavaClassName(javaClassName.getValue());
		boolean isValidPackageName = ProjectBeanValidator.isValidPackageName(packageName.getValue());

		if (isValidClassName && isValidPackageName) {
			alertDialog.dismiss();
			// TODO: Create Java Class
			Toast.makeText(projectEditorActivity, "Not implemented yet", Toast.LENGTH_SHORT).show();
		} else {
			if (!isValidClassName) {
				javaClassName.postValue(javaClassName.getValue());
			} else if (!isValidPackageName) {
				packageName.postValue(packageName.getValue());
			}
		}
	}

	public void dismissDialog() {
		alertDialog.dismiss();
	}

	public void setProjectFile(ProjectFile projectFile) {
		this.projectFile = projectFile;
		packageName.postValue(projectFile.getProjectBean().getProjectPackageName());
	}

	public void setAlertDialog(AlertDialog alertDialog) {
		this.alertDialog = alertDialog;
	}

	public void setProjectEditorActivity(ProjectEditorActivity projectEditorActivity) {
		this.projectEditorActivity = projectEditorActivity;
	}

	public MutableLiveData<String> getPackageName() {
		return this.packageName;
	}

	public MutableLiveData<String> getJavaClassName() {
		return this.javaClassName;
	}
}
