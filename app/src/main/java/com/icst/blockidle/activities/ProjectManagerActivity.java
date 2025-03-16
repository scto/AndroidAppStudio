package com.icst.blockidle.activities;

import com.icst.blockidle.R;
import com.icst.blockidle.databinding.ActivityProjectManagerBinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

public class ProjectManagerActivity extends AppCompatActivity {

	private ActivityProjectManagerBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SplashScreen.installSplashScreen(this);
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);

		// Inflate and get instance of binding
		binding = ActivityProjectManagerBinding.inflate(getLayoutInflater());

		// set content view to binding's root
		setContentView(binding.getRoot());

		// Setup Toolbar
		binding.toolbar.setTitle(getString(R.string.app_name));
		setSupportActionBar(binding.toolbar);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.binding = null;
	}
}
