/*
 * This file is part of Android AppStudio [https://github.com/Innovative-CST/AndroidAppStudio].
 *
 * License Agreement
 * This software is licensed under the terms and conditions outlined below. By accessing, copying, modifying, or using this software in any way, you agree to abide by these terms.
 *
 * 1. **  Copy and Modification Restrictions  **
 *    - You are not permitted to copy or modify the source code of this software without the permission of the owner, which may be granted publicly on GitHub Discussions or on Discord.
 *    - If permission is granted by the owner, you may copy the software under the terms specified in this license agreement.
 *    - You are not allowed to permit others to copy the source code that you were allowed to copy by the owner.
 *    - Modified or copied code must not be further copied.
 * 2. **  Contributor Attribution  **
 *    - You must attribute the contributors by creating a visible list within the application, showing who originally wrote the source code.
 *    - If you copy or modify this software under owner permission, you must provide links to the profiles of all contributors who contributed to this software.
 * 3. **  Modification Documentation  **
 *    - All modifications made to the software must be documented and listed.
 *    - the owner may incorporate the modifications made by you to enhance this software.
 * 4. **  Consistent Licensing  **
 *    - All copied or modified files must contain the same license text at the top of the files.
 * 5. **  Permission Reversal  **
 *    - If you are granted permission by the owner to copy this software, it can be revoked by the owner at any time. You will be notified at least one week in advance of any such reversal.
 *    - In case of Permission Reversal, if you fail to acknowledge the notification sent by us, it will not be our responsibility.
 * 6. **  License Updates  **
 *    - The license may be updated at any time. Users are required to accept and comply with any changes to the license.
 *    - In such circumstances, you will be given 7 days to ensure that your software complies with the updated license.
 *    - We will not notify you about license changes; you need to monitor the GitHub repository yourself (You can enable notifications or watch the repository to stay informed about such changes).
 * By using this software, you acknowledge and agree to the terms and conditions outlined in this license agreement. If you do not agree with these terms, you are not permitted to use, copy, modify, or distribute this software.
 *
 * Copyright © 2024 Dev Kumar
 */

package com.icst.android.appstudio.activities;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;

import com.icst.android.appstudio.R;
import com.icst.android.appstudio.block.model.FileModel;
import com.icst.android.appstudio.databinding.ActivityEventsBinding;
import com.icst.android.appstudio.dialogs.SourceCodeViewerDialog;
import com.icst.android.appstudio.fragments.events.EventListFragment;
import com.icst.android.appstudio.helper.FileModelCodeHelper;
import com.icst.android.appstudio.models.EventHolder;
import com.icst.android.appstudio.models.ModuleModel;
import com.icst.android.appstudio.utils.EnvironmentUtils;
import com.icst.android.appstudio.utils.EventsHolderUtils;
import com.icst.android.appstudio.utils.serialization.DeserializerUtils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class EventsActivity extends BaseActivity {

	private ActivityEventsBinding binding;
	/*
	 * Contains the location of currently selected file model.
	 * For example: /../../Project/100/../abc/FileModel
	 */
	private File fileModelDirectory;
	/*
	 * Contains the location of currently loaded event list.
	 * For example: /../../Project/100/../abc/Events
	 */
	private File eventsDir;

	private ModuleModel module;

	private MenuItem showSourceCode;
	private FileModel fileModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize the variables
		module = new ModuleModel();
		module.init(
				getIntent().getStringExtra("module"),
				new File(getIntent().getStringExtra("projectRootDirectory")));
		fileModelDirectory = new File(getIntent().getStringExtra("fileModelDirectory"));
		eventsDir = new File(fileModelDirectory.getParentFile(), EnvironmentUtils.EVENTS_DIR);
		binding = ActivityEventsBinding.inflate(getLayoutInflater());

		// Sets the layout of Activity
		setContentView(binding.getRoot());

		// Handles Toolbar
		binding.toolbar.setTitle(R.string.app_name);
		setSupportActionBar(binding.toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

		DeserializerUtils.deserialize(
				fileModelDirectory,
				new DeserializerUtils.DeserializerListener() {

					@Override
					public void onSuccessfullyDeserialized(Object object) {
						if (object instanceof FileModel) {
							fileModel = (FileModel) object;
						}
					}

					@Override
					public void onFailed(int errorCode, Exception e) {
					}
				});

		if (fileModel == null) {
			Toast.makeText(this, R.string.failed_to_deserialize_file_model, Toast.LENGTH_SHORT).show();
			finish();
		}

		Executors.newSingleThreadExecutor()
				.execute(
						() -> {
							ArrayList<EventHolder> eventHolderList = EventsHolderUtils.getEventHolder(eventsDir);
							runOnUiThread(
									() -> {
										// Loading the events
										loadEventData(eventHolderList);
									});
						});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		binding = null;
	}

	/*
	 * Loads the NavigationRail View and Events.
	 */
	private void loadEventData(ArrayList<EventHolder> eventHolderList) {
		/*
		 * ** Loads the Navigation menu **
		 * Adds the MenuItem in NavigationRail.
		 * Retreviews the MenuItem Icon from EventHolder.
		 * Set the current fragment if EventList of a Fragment is attached to a
		 * FileModel.
		 */
		for (int position = 0; position < eventHolderList.size(); ++position) {
			Menu menu = binding.navigationRail.getMenu();
			MenuItem item = menu.add(Menu.NONE, position, Menu.NONE, eventHolderList.get(position).getHolderName());
			item.setIcon(getEventHolderIcon(this, eventHolderList.get(position)));
			if (eventHolderList.get(position).isBuiltInEvents()) {
				getSupportFragmentManager()
						.beginTransaction()
						.replace(
								R.id.fragment_container,
								new EventListFragment(
										module,
										fileModelDirectory,
										new File(
												eventHolderList.get(position).getFilePath(),
												EnvironmentUtils.EVENTS_DIR),
										eventHolderList.get(position).getDisableNewEvents()))
						.commit();
			}
		}
		/*
		 * Sets the click listener of NavigationRail.
		 * Treating the MenuItem Id as position of ArrayList
		 * Retreview the FilePath of EventList from EventHolder itself.
		 */
		binding.navigationRail.setOnItemSelectedListener(
				(menuItem) -> {
					int position = menuItem.getItemId();
					getSupportFragmentManager()
							.beginTransaction()
							.replace(
									R.id.fragment_container,
									new EventListFragment(
											module,
											fileModelDirectory,
											new File(
													eventHolderList.get(position).getFilePath(),
													EnvironmentUtils.EVENTS_DIR),
											eventHolderList.get(position).getDisableNewEvents()))
							.commit();
					return true;
				});
	}

	public static Drawable getEventHolderIcon(Context context, EventHolder holder) {
		if (holder.getIcon() != null) {
			return new BitmapDrawable(
					context.getResources(),
					BitmapFactory.decodeByteArray(holder.getIcon(), 0, holder.getIcon().length));
		}
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_show_code, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		showSourceCode = menu.findItem(R.id.show_source_code);
		if (fileModel == null) {
			if (showSourceCode != null) {
				showSourceCode.setVisible(false);
			}
		} else {
			if (showSourceCode != null) {
				showSourceCode.setVisible(true);
			}
		}
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (menuItem.getItemId() == R.id.show_source_code) {
			if (fileModel != null) {
				FileModelCodeHelper helper = new FileModelCodeHelper();
				helper.setFileModel(fileModel);
				helper.setEventsDirectory(eventsDir);
				helper.setProjectRootDirectory(module.projectRootDirectory);
				SourceCodeViewerDialog sourceCodeDialog = new SourceCodeViewerDialog(this, fileModel, helper.getCode());
				sourceCodeDialog.create().show();
			}
		}

		return super.onOptionsItemSelected(menuItem);
	}
}
