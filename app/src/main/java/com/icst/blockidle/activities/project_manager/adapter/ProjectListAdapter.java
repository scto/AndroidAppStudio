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

package com.icst.blockidle.activities.project_manager.adapter;

import java.util.ArrayList;

import com.icst.blockidle.databinding.ProjectListItemBinding;
import com.icst.blockidle.util.ProjectFile;
import com.icst.blockidle.viewmodel.ProjectAdapterViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {

	private final ArrayList<ProjectFile> data;
	private ProjectListItemBinding binding;

	public ProjectListAdapter(ArrayList<ProjectFile> data) {
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
		mProjectAdapterViewModel.setProjectFile(data.get(position));
		mProjectAdapterViewModel.setActivity(AppCompatActivity.class.cast(binding.getRoot().getContext()));
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
