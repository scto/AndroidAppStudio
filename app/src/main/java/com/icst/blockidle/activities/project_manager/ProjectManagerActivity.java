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

import java.util.Objects;

import com.icst.blockidle.R;
import com.icst.blockidle.activities.project_manager.adapter.ProjectListAdapter;
import com.icst.blockidle.databinding.ActivityProjectManagerBinding;
import com.icst.blockidle.util.EnvironmentUtils;
import com.icst.blockidle.viewmodel.ProjectManagerViewModel;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ProjectManagerActivity extends AppCompatActivity {

	private ActivityProjectManagerBinding binding;
	private ProjectListAdapter adapter;
	private ProjectManagerViewModel mProjectManagerViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SplashScreen.installSplashScreen(this);
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		EnvironmentUtils.init(this);

		mProjectManagerViewModel = new ViewModelProvider(this).get(ProjectManagerViewModel.class);
		mProjectManagerViewModel.setActivity(this);

		// Inflate and get instance of binding
		binding = ActivityProjectManagerBinding.inflate(getLayoutInflater());
		binding.setViewModel(mProjectManagerViewModel);

		// set content view to binding's root
		setContentView(binding.getRoot());

		// Calling Methods
		UI();
	}

	@SuppressLint("NotifyDataSetChanged")
	private void UI() {
		// System Padding
		ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		// Toolbar
		setSupportActionBar(binding.toolbar);
		Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		binding.toolbar.setTitle(R.string.app_name);

		mProjectManagerViewModel.getProjects()
				.observe(this, data -> adapter.notifyDataSetChanged());

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