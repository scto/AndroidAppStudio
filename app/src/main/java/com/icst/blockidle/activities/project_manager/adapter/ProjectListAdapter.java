package com.icst.blockidle.activities.project_manager.adapter;

import java.util.ArrayList;

import com.icst.blockidle.bean.ProjectBean;
import com.icst.blockidle.databinding.ProjectListItemBinding;
import com.icst.blockidle.viewmodel.ProjectAdapterViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

	private final ArrayList<ProjectBean> data;
	private ProjectListItemBinding binding;

	public ProjectListAdapter(ArrayList<ProjectBean> data) {
		this.data = data;
	}

	@NonNull @Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		binding = ProjectListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
		return new ViewHolder(binding.getRoot());
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		ProjectAdapterViewModel mProjectAdapterViewModel = new ProjectAdapterViewModel();
		mProjectAdapterViewModel.setProjectBean(data.get(position));
		binding.setViewModel(mProjectAdapterViewModel);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(@NonNull View binding) {
			super(binding);
		}
	}
}
