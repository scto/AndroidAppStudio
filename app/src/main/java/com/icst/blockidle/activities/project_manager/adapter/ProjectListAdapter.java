package com.icst.blockidle.activities.project_manager.adapter;

import java.util.ArrayList;

import com.icst.blockidle.bean.ProjectBean;
import com.icst.blockidle.databinding.ProjectListItemBinding;
import com.icst.blockidle.viewmodel.ProjectAdapterViewModel;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

	private final ArrayList<ProjectBean> data;

	public ProjectListAdapter(ArrayList<ProjectBean> data) {
		this.data = data;
	}

	@NonNull @Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new ViewHolder(
				ProjectListItemBinding.inflate(LayoutInflater.from(parent.getContext())));
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		ProjectListItemBinding binding = holder.getProjectListItemBinding();

		// Get a ViewModel
		ProjectAdapterViewModel mProjectAdapterViewModel = new ProjectAdapterViewModel();

		// Set ViewModel data
		mProjectAdapterViewModel.setProjectBean(data.get(position));

		binding.setViewModel(mProjectAdapterViewModel);
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		private ProjectListItemBinding projectListItemBinding;

		public ViewHolder(@NonNull ProjectListItemBinding binding) {
			super(binding.getRoot());
			projectListItemBinding = binding;
		}

		public ProjectListItemBinding getProjectListItemBinding() {
			return this.projectListItemBinding;
		}

		public void setProjectListItemBinding(ProjectListItemBinding projectListItemBinding) {
			this.projectListItemBinding = projectListItemBinding;
		}
	}
}
