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

import com.icst.blockidle.activities.project_manager.adapter.dialog.ProjectConfigurationDialog;
import com.icst.blockidle.bean.ProjectBean;
import com.icst.blockidle.exception.ProjectUpdateException;
import com.icst.blockidle.listener.ProjectConfigurationDialogListener;
import com.icst.blockidle.repository.ProjectRepository;
import com.icst.blockidle.util.ProjectFile;

import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

public class ProjectAdapterViewModel extends ViewModel {

	private ProjectFile projectFile;
	private AppCompatActivity activity;

	// Dialog
	private AlertDialog alertDialog;
	private ProjectConfigurationDialog dialog;
	private ProjectConfigurationDialogListener projectDialogConfigListener;

	public ProjectAdapterViewModel() {
		projectDialogConfigListener = new ProjectConfigurationDialogListener() {

			@Override
			public void onCreateNewProject(ProjectBean newProjectBean) {
				ProjectRepository.getInstance().createProject(newProjectBean);
				alertDialog.dismiss();
			}

			@Override
			public void onProjectConfigChange(ProjectBean projectBean) {
				projectFile.setProjectBean(projectBean);
				try {
					ProjectRepository.getInstance().updateProject(projectFile);
					alertDialog.dismiss();
				} catch (ProjectUpdateException e) {
					Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onCancel() {
				alertDialog.dismiss();
			}

		};

		dialog = new ProjectConfigurationDialog(activity, projectFile.getProjectBean(), projectDialogConfigListener);
		alertDialog = dialog.create();
	}

	public void setProjectFile(ProjectFile projectFile) {
		this.projectFile = projectFile;
	}

	public String getProjectName() {
		return projectFile.getProjectBean().getProjectName();
	}

	public String getProjectVersionName() {
		return projectFile.getProjectBean().getProjectVersionName();
	}

	public String getProjectNameFirstLetter() {
		return String.valueOf(getProjectName().charAt(0));
	}

	public void onProjectSelected() {
		if (alertDialog != null) {
			alertDialog.dismiss();
		}
		alertDialog.show();
	}

	public AppCompatActivity getActivity() {
		return this.activity;
	}

	public void setActivity(AppCompatActivity activity) {
		this.activity = activity;
	}
}
