package com.icst.blockidle.activities.project_manager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.icst.blockidle.R;
import com.icst.blockidle.activities.project_manager.adapter.ProjectListAdapter;
import com.icst.blockidle.databinding.ActivityProjectManagerBinding;

import java.util.ArrayList;

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

        //Calling Methods
        UI();
    }

    private void UI() {
        //System Padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            data.add("Block-IDE");
        }

        //List
        binding.projectList.setLayoutManager(new LinearLayoutManager(this));
        binding.projectList.setAdapter(new ProjectListAdapter(data));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
