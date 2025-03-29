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

package com.icst.blockidle.activities.project_editor;

import com.icst.blockidle.activities.project_editor.viewholder.FileTreeViewHolder;
import com.icst.blockidle.databinding.ActivityProjectEditorBinding;
import com.icst.blockidle.exception.IDLEFileAlreadyExistsException;
import com.icst.blockidle.util.IDLEFolder;
import com.icst.blockidle.util.ProjectFile;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.unnamed.b.atv.view.TreeNodeWrapperView;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProjectEditorActivity extends AppCompatActivity {

	private ActivityProjectEditorBinding binding;
	private ProjectFile projectFile;
	private IDLEFolder rootFolder;

	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);

		binding = ActivityProjectEditorBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			projectFile = getIntent().getParcelableExtra("projectFile", ProjectFile.class);
		} else {
			projectFile = ProjectFile.class.cast(getIntent().getParcelableExtra("projectFile"));
		}

		rootFolder = IDLEFolder.getProjectIDLEFolder(projectFile);

		binding.toolbar.setTitle(projectFile.getProjectBean().getProjectName());
		binding.toolbar.setSubtitle(projectFile.getProjectBean().getProjectPackageName());

		setSupportActionBar(binding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		TreeNode root = TreeNode.root();
		TreeNode child = new TreeNode(rootFolder);
		child.setViewHolder(new FileTreeViewHolder(this));

		root.addChild(child);

		AndroidTreeView tView = new AndroidTreeView(this, root);
		tView.setDefaultAnimation(true);
		TreeNodeWrapperView treeWrapper = new TreeNodeWrapperView(this, com.unnamed.b.atv.R.style.TreeNodeStyle);
		treeWrapper.getNodeContainer().addView(tView.getView());

		binding.fileTreeContainer.addView(treeWrapper);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		binding = null;
	}
}
