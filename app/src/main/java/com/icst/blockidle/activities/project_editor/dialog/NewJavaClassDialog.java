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

package com.icst.blockidle.activities.project_editor.dialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.icst.blockidle.activities.project_editor.ProjectEditorActivity;
import com.icst.blockidle.databinding.DialogCreateJavaFileBinding;
import com.icst.blockidle.util.IDLEFolder;
import com.icst.blockidle.util.ProjectFile;

import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

public class NewJavaClassDialog extends MaterialAlertDialogBuilder {

	private ProjectEditorActivity projectEditorActivity;
	private IDLEFolder javaDir;
	private ProjectFile projectFile;
	private AlertDialog alertDialog;

	public NewJavaClassDialog(
			ProjectEditorActivity projectEditorActivity,
			IDLEFolder javaDir,
			ProjectFile projectFile) {
		super(projectEditorActivity);
		this.projectEditorActivity = projectEditorActivity;
		this.javaDir = javaDir;
		this.projectFile = projectFile;

		DialogCreateJavaFileBinding binding = DialogCreateJavaFileBinding
				.inflate(LayoutInflater.from(projectEditorActivity));
		binding.packageName.setText(projectFile.getProjectBean().getProjectPackageName());
		setView(binding.getRoot());
		alertDialog = create();
		alertDialog.show();
		binding.create.setOnClickListener(v -> {
			alertDialog.dismiss();
			// TODO: Create Java Class If valid input
		});

		binding.cancel.setOnClickListener(v -> {
			alertDialog.dismiss();
		});
	}

}
