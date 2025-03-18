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

package com.icst.blockidle.activities.project_manager;

import java.util.ArrayList;

import com.icst.blockidle.R;
import com.icst.blockidle.activities.project_manager.adapter.ProjectListAdapter;
import com.icst.blockidle.databinding.ActivityProjectManagerBinding;
import com.icst.blockidle.util.EnvironmentUtils;
import com.icst.blockidle.util.ProjectFile;
import com.icst.blockidle.viewmodel.ProjectManagerViewModel;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ProjectManagerActivity extends AppCompatActivity {

	private ActivityProjectManagerBinding binding;
	private ProjectListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SplashScreen.installSplashScreen(this);
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		EnvironmentUtils.init(this);

		// Inflate and get instance of binding
		binding = ActivityProjectManagerBinding.inflate(getLayoutInflater());

		// set content view to binding's root
		setContentView(binding.getRoot());

		// Calling Methods
		UI();
	}

	private void UI() {
		// System Padding
		ViewCompat.setOnApplyWindowInsetsListener(
				binding.getRoot(),
				(v, insets) -> {
					Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
					v.setPadding(
							systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
					return insets;
				});

		// Toolbar
		setSupportActionBar(binding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		binding.toolbar.setTitle(R.string.app_name);

		ProjectManagerViewModel mProjectManagerViewModel = new ViewModelProvider(this)
				.get(ProjectManagerViewModel.class);
		mProjectManagerViewModel
				.getProjects()
				.observe(
						this,
						new Observer<ArrayList<ProjectFile>>() {

							@Override
							public void onChanged(ArrayList<ProjectFile> data) {
								adapter.notifyDataSetChanged();
							}
						});
		// List
		adapter = new ProjectListAdapter(mProjectManagerViewModel.getProjects().getValue());
		binding.projectList.setLayoutManager(new LinearLayoutManager(this));
		binding.projectList.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.binding = null;
	}
}
